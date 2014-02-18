/**
 * Project Name:timesheet
 * File Name:TestFileSort.java
 * Package Name:org.learn
 * Date:2014-2-18ÏÂÎç09:08:17
 * Copyright (c) 2014, https://github.com/seiyaa All Rights Reserved.
 *
 */
package org.learn;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class FileUtil
{
  public static List<File> listAllFiles(String path)
  {
    LinkedList<String> folderList = new LinkedList<String>();
    folderList.add(path);
    ArrayList<File> fileList = new ArrayList<File>();

    while (folderList.size() > 0) {
      File file = new File((String)folderList.poll());
      File[] files = file.listFiles();
      for (int i = 0; i < files.length; i++) {
        if (files[i].isDirectory())
          folderList.add(files[i].getPath());
        else {
          fileList.add((File) files[i]);
        }
      }
    }
    return fileList;
  }

  public static void main(String[] args) {
    String path = "E://RepositoryMaven//ant";
    List<File> fileList = listAllFiles(path);
    Comparator<File> fileSizeComparator = new FileSizeComparator();
    
    Collections.sort(fileList,fileSizeComparator);
    for(int i = 0;i<10;i++)
        System.out.println(fileList.get(i).getName() + ":" +fileList.get(i).length());
  }
}