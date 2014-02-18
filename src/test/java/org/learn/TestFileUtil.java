/**
 * Project Name:timesheet
 * File Name:TestFileSort.java
 * Package Name:org.learn
 * Date:2014-2-18ÏÂÎç09:08:17
 * Copyright (c) 2014, https://github.com/seiyaa All Rights Reserved.
 *
 */
package org.learn;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class TestFileUtil
{
    @Test
    public void testFileSort()
    {
        String path = "E://RepositoryMaven//ant";
        List<File> fileList = FileUtil.listAllFiles(path);
        Comparator<File> fileSizeComparator = new FileSizeComparator();
        Collections.sort(fileList, fileSizeComparator);

        for (int i = 0; i < fileList.size() - 2; i++)
        {
            File fileBigger = fileList.get(i);
            File fileSmaller = fileList.get(i + 1);
            assertTrue(fileBigger.length() >= fileSmaller.length());
        }
    }
}