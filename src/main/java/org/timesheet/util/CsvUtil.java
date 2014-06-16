/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-5-27
 */

package org.timesheet.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

public class CsvUtil {
    
    /**
     * 根据文件路径转换为对应bean
     * @param filePath
     * @param clazz
     * @return
     * @throws IOException
     */
    public static <T> List<T> getBeanFromCsv(BufferedReader reader, Class<T> clazz,String[] columns) throws IOException {
        ColumnPositionMappingStrategy<T> strat = new ColumnPositionMappingStrategy<T>();
        strat.setType(clazz);
        strat.setColumnMapping(columns);
        CsvToBean<T> csv = new CsvToBean<T>();
        
        CSVReader csvReader = new CSVReader(reader, CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 1);

        List<T> list = csv.parse(strat, csvReader);
        return list;        
    }
}
