/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-4-11
 * description: 
 */

package csv;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;

public class TestOpencsvReadWrite {

    @Test
    public void testCsvRead() throws IOException {
        String csvFileName = TestOpencsvReadWrite.class.getResource("sample.csv").getFile();
        CSVReader csvReader = new CSVReader(new FileReader(csvFileName));
        List<String[]> content = csvReader.readAll();

        for (String[] row : content) {
            System.out.println(row[0] + " # " + row[1] + " #  " + row[2]);
        }

        csvReader.close();
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
