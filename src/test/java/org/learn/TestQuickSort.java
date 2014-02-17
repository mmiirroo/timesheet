/**
 * Project Name:timesheet
 * File Name:QuickSort.java
 * Package Name:org.learn.sort
 * Date:2014-2-17ÏÂÎç10:17:57
 * Copyright (c) 2014, https://github.com/seiyaa All Rights Reserved.
 *
 */
package org.learn;

import org.junit.Test;

public class TestQuickSort
{
    @Test
    public void testSort()
    {
        Integer[] source = { 4, 2, 1, 5, 6, 8, 3 };
        quickSort(source, 0, source.length - 1);
        for (int ii : source)
            System.out.println(ii);
    }

    public <T extends Comparable<? super T>> void quickSort(T[] source, int start, int end)
    {
        int i = start;
        int j = end;

        if ((source == null) || (source.length == 0))
            return;

        while (i < j)
        {
            while (i < j && source[j].compareTo(source[i]) >= 0)
            {
                j--;
            }
            if(i<j)
                swap(source, i, j);
            while ( i < j && source[i].compareTo(source[j]) < 0)
            {
                i++;
            }
            if(i<j)
                swap(source, i, j);
        }
        
        if (start < i - 1)
            quickSort(source, start, i - 1);
        if (i + 1 < end)
            quickSort(source, i + 1, end);
    }

    public static <T> void swap(T[] source, int i, int j)
    {
        T temp = source[i];
        source[i] = source[j];
        source[j] = temp;
    }
}
