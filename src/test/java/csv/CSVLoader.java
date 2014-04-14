/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-4-12
 * description: 
 */

package csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.timesheet.DateUtil;

import au.com.bytecode.opencsv.CSVReader;

public class CSVLoader {
    private static final Log LOG = LogFactory.getLog(CSVLoader.class);

    private static final String SQL_INSERT = "insert into ${table} (${keys}) values (${values})";
    private static final String TABLE_REGEX = "\\$\\{table\\}";
    private static final String KEYS_REGEX = "\\$\\{keys\\}";
    private static final String VALUES_REGEX = "\\$\\{values\\}";

    private Connection connection;
    private char separator;

    public CSVLoader(Connection connection) {
        this.connection = connection;
        this.separator = ',';
    }

    public void loadCSV(String csvFile, String tableName, boolean truncateBeforeLoad)
            throws Exception {
        CSVReader csvReader = null;
        if (null == this.connection) {
            throw new Exception("Not a valid connection");
        }

        try {
            csvReader = new CSVReader(new FileReader(csvFile), this.separator);

        } catch (Exception e) {
            LOG.error("Error occured while excuting file.", e);
            throw new Exception("Error occured while excuting file." + e.getMessage());
        }

        String[] headerRow = csvReader.readNext();
        if (null == headerRow) {
            throw new FileNotFoundException("No columns defined in given csv file."
                    + "Please check the csv file format");
        }
        
        String questionmarks = StringUtils.repeat("?,", headerRow.length);
        questionmarks = (String) questionmarks.subSequence(0, questionmarks.length()-1);
        
        String insertDML = SQL_INSERT.replaceFirst(TABLE_REGEX, tableName);
        insertDML = insertDML.replaceFirst(KEYS_REGEX, StringUtils.join(headerRow, ","));
        insertDML = insertDML.replaceFirst(VALUES_REGEX, questionmarks);
        LOG.info("insertDML:"+insertDML);
        
        String[] nextLine;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = this.connection;
            con.setAutoCommit(false);
            ps = con.prepareStatement(insertDML);
            
            if(truncateBeforeLoad) {
                con.createStatement().execute("DELETE FROM" + tableName);
            }
            
            final int batchSize = 1000;
            int count = 0;
            Date date = null;
            while( (nextLine= csvReader.readNext()) !=null ) {
                int index=1;
                for(String string :nextLine) {
                    date = DateUtil.convertToDate(string);
                    if(null!=date) {
                        ps.setDate(index++, new java.sql.Date(date.getTime()));
                    } else {
                        ps.setString(index++, string);
                    }
                }
                ps.addBatch();
                
                if (++count % batchSize == 0) {
                    ps.executeBatch();
                }
            }  
            
            ps.executeBatch();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            LOG.error("Error occured while loading data from file to database.",e);
            throw new Exception(
                    "Error occured while loading data from file to database."
                            + e.getMessage());
        } finally {
            if(null!=ps)
                ps.close();
            if(null!=con)
                con.close();
            
            csvReader.close();
        }
    }
    
    public char getSeparator() {
        return separator;
    }
 
    public void setSeparator(char seprator) {
        this.separator = seprator;
    }
}