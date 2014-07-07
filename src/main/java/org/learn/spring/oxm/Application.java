/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-7-7
 */

package org.learn.spring.oxm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;


public class Application {
    private static final String FILE_NAME=Application.class.getResource("settings.xml").getPath();

    private Settings settings = new Settings();
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    
    public void saveSettings() throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(FILE_NAME);
            this.marshaller.marshal(settings, new StreamResult(fos));
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(fos!=null) {
                fos.close();
            }
        }
    }
    
    public void loadSettings() throws IOException {
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(FILE_NAME);
            this.settings = (Settings) this.unmarshaller.unmarshal(new StreamSource(fis));
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(fis!=null) {
                fis.close();
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("persistence-beans.xml");
        Application application = (Application) appContext.getBean("application");
        
        application.saveSettings();
        application.loadSettings();
    }
    
    public Settings getSettings() {
        return settings;
    }
    public void setSettings(Settings settings) {
        this.settings = settings;
    }
    public Marshaller getMarshaller() {
        return marshaller;
    }
    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }
    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }
    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }
}
