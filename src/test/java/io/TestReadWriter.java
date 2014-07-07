/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-6-30
 */

package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

public class TestReadWriter {

    public static void main(String[] args) throws IOException {
        File directory = new File("");// 设定为当前文件夹
        System.out.println(directory.getCanonicalPath());// 获取标准的路径
        System.out.println(directory.getAbsolutePath());// 获取绝对路径

        File file = new File(directory+"target\\arthinking.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        bw.write("arthinking");
        bw.close();
        
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String str = br.readLine();
        while(null!=str) {
            System.out.println(str);
            str = br.readLine();
        }
        br.close();
        
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.writeInt(1);
        raf.writeChar('a');
        raf.seek(0);
        
    }
}
