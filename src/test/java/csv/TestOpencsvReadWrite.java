/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-4-11
 * description: 
 */

package csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

public class TestOpencsvReadWrite {

    @Test
    public void testCsvRead() throws IOException {
//        String csvFileName = TestOpencsvReadWrite.class.getResource("sample.csv").getFile();
//        CSVReader csvReader = new CSVReader(new FileReader(csvFileName));

        URL url = new URL("http://public-api.nj.pla.tuniu.org/filebroker/cdn/prd/cf/5b/cf5b239a4113dd0b86e31c2fd249739a.csv");
        URLConnection conn = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> content = csvReader.readAll();

        for (String[] row : content) {
            System.out.println(row[0] + " # " + row[1] + " #  " + row[2]);
        }

        csvReader.close();
    }

    @Test
    public void testCsvToBean() throws FileNotFoundException {
        ColumnPositionMappingStrategy<Country> strat = new ColumnPositionMappingStrategy<Country>();
        strat.setType(Country.class);
        String[] columns = new String[]{"countryName","capital"};
        strat.setColumnMapping(columns);
        CsvToBean<Country> csv = new CsvToBean<Country>();
        String csvFileName = TestOpencsvReadWrite.class.getResource("sample.csv").getFile();
        CSVReader csvReader = new CSVReader(new FileReader(csvFileName), CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 1);

        List<Country> list = csv.parse(strat, csvReader);
        for(Country object:list) {
            System.out.println(object);
        }
    }
    
    @Test
    public void testParseXml() throws DocumentException {
        File xmlFile = new File(TestOpencsvReadWrite.class.getResource("sample.xml").getFile());
        SAXReader reader = new SAXReader();
        Document doc = reader.read(xmlFile);
        doc.accept(new MyVisitor());

    }

    @Test
    public void testWriteXml() throws IOException {
        XMLWriter writer = new XMLWriter(new FileWriter("target/author.xml"));
        Document doc = createDoc();
        writer.write(doc);
        writer.close();
    }

    private Document createDoc() {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("root");
        root.addElement("author").addAttribute("name", "Kree").addAttribute("location", "UK")
                .addText("Kree Strachan");
        root.addElement("author").addAttribute("name", "King").addAttribute("location", "US")
                .addText("King McWrirter");
        return doc;
    }
}
