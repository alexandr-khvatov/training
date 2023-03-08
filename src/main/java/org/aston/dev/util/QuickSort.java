package org.aston.dev.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QuickSort {
    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        sort(list, 0, list.size() - 1, c);
    }

    public static <T> void sort(List<T> list, int from, int to, Comparator<? super T> c) {
        if (from < to) {
            int divideIndex = partition(list, from, to, c);
            sort(list, from, divideIndex - 1, c);
            sort(list, divideIndex, to, c);
        }
    }

    private static <T> int partition(List<T> list, int from, int to, Comparator<? super T> c) {
        int rightIndex = to;
        int leftIndex = from;

        T pivot = list.get(from + (to - from) / 2);
        while (leftIndex <= rightIndex) {

            while (c.compare(list.get(leftIndex), pivot) < 0) {
                leftIndex++;
            }

            while (c.compare(list.get(rightIndex), pivot) > 0) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                Collections.swap(list, rightIndex, leftIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }
}