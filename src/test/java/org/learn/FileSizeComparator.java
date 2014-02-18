/**
 * Project Name:timesheet
 * File Name:UserDefinedFile.java
 * Package Name:org.learn.sort
 * Date:2014-2-18обнГ10:11:56
 * Copyright (c) 2014, https://github.com/seiyaa All Rights Reserved.
 *
 */
package org.learn;

import java.io.File;
import java.util.Comparator;

public class FileSizeComparator implements Comparator<File>
{
    @Override
    public int compare(File file1, File file2)
    {
        if (file1.length() == ((File) file2).length())
            return 0;
        else if (file1.length() > ((File) file2).length())
            return -1;
        else
            return 1;
    }
}
