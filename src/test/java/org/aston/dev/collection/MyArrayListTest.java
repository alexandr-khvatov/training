package org.aston.dev.collection;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    private List<Integer> list = new MyArrayList<>(List.of(10, 9, 7, 0, 21, 43, 15, 19));
    private int size = 8;

    @Test
    void add_shouldAddElement_whenSuccess() {
        assertEquals(size, list.size());
        list.add(777);
        assertEquals(9, list.size());
        assertEquals(777, list.get(list.size() - 1));
    }

    @Test
    void add_shouldAddElementByIndex_whenSuccess() {
        assertEquals(size, list.size());
        list.add(0, 777);
        assertEquals(9, list.size());
        assertEquals(777, list.get(0));
        assertArrayEquals(List.of(777, 10, 9, 7, 0, 21, 43, 15, 19).toArray(), list.toArray());
        list.add(5, 777);
        assertEquals(777, list.get(5));
        assertArrayEquals(List.of(777, 10, 9, 7, 0, 777, 21, 43, 15, 19).toArray(), list.toArray());
    }

    @Test
    public void get_shouldReturnElementByIndex_whenSuccess() {
        assertEquals(10, list.get(0));
        assertNotEquals(9, list.get(0));
        assertEquals(0, list.get(3));
    }

    @Test
    public void get_shouldThrowsExc_whenIndexIsWrong() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(8)
        );
    }

    @Test
    void remove_shouldRemoveByIndex_whenSuccess() {
        assertEquals(size, list.size());
        assertEquals(19, list.get(list.size() - 1));
        list.remove(7);
        assertEquals(7, list.size());
        assertEquals(15, list.get(list.size() - 1));
    }

    @Test
    void clear_shouldClearedList_whenSuccess() {
        assertEquals(size, list.size());
        list.clear();
        assertAll(
                () -> assertEquals(0, list.size()),
                () -> assertTrue(list.isEmpty())
        );
    }

    @Test
    void size_shouldReturnSize_whenSuccess() {
        var expected = size;
        assertEquals(expected, this.list.size());
        list.remove(list.size() - 1);
        assertEquals(expected - 1, this.list.size());
        list.clear();
        assertEquals(0, this.list.size());
    }

    @Test
    void isEmpty_shouldEmpty_whenSizeEquals0() {
        var emptyList = new MyArrayList<>();
        var nonEmptyListWithCallClear = new MyArrayList<>(List.of(10));
        nonEmptyListWithCallClear.clear();

        assertAll(
                () -> assertTrue(emptyList.isEmpty()),
                () -> assertTrue(nonEmptyListWithCallClear.isEmpty()),
                () -> assertFalse(list.isEmpty())
        );
    }

    @Test
    void sort() {
        var expected = new MyArrayList<>(List.of(0, 7, 9, 10, 15, 19, 21, 43));
        this.list.sort(Integer::compareTo);
        assertArrayEquals(expected.toArray(), list.toArray());
    }
}