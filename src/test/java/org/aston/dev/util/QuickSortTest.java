package org.aston.dev.util;

import org.aston.dev.collection.MyArrayList;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuickSortTest {

    @ParameterizedTest
    @MethodSource("getArguments")
    <T> void sort_shouldSortArray_whenSuccessSorted(List<T> list, List<T> expected, Comparator<T> comparator) {
        QuickSort.sort(list, comparator);
        assertArrayEquals(expected.toArray(), list.toArray());
    }

    static Stream<Arguments> getArguments() {
        var integerComparator = new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return Integer.compare(i1, i2);
            }
        };

        var timeComparator = new Comparator<LocalTime>() {
            public int compare(LocalTime t1, LocalTime t2) {
                return t1.compareTo(t2);
            }
        };

        return Stream.of(
                Arguments.of(
                        new MyArrayList<>(List.of(10, 9, 7, 0, 21, 43, 15, 19)),
                        new MyArrayList<>(List.of(0, 7, 9, 10, 15, 19, 21, 43)),
                        integerComparator),

                Arguments.of(
                        new MyArrayList<>(List.of(1, 0, 1)),
                        new MyArrayList<>(List.of(0, 1, 1)),
                        integerComparator),

                Arguments.of(
                        new MyArrayList<>(List.of(1, 0)),
                        new MyArrayList<>(List.of(0, 1)),
                        integerComparator),

                Arguments.of(
                        new MyArrayList<>(List.of(1)),
                        new MyArrayList<>(List.of(1)),
                        integerComparator),

                Arguments.of(
                        new MyArrayList<>(List.of()),
                        new MyArrayList<>(List.of()),
                        integerComparator),

                Arguments.of(
                        new MyArrayList<>(List.of(
                                LocalTime.of(10, 0),
                                LocalTime.of(12, 0),
                                LocalTime.of(13, 0),
                                LocalTime.of(1, 0),
                                LocalTime.of(7, 0))
                        ),
                        new MyArrayList<>(List.of(
                                LocalTime.of(1, 0),
                                LocalTime.of(7, 0),
                                LocalTime.of(10, 0),
                                LocalTime.of(12, 0),
                                LocalTime.of(13, 0))
                        ),
                        timeComparator)
        );
    }
}