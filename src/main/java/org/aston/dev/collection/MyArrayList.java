package org.aston.dev.collection;

import java.util.*;

import static java.util.Objects.isNull;

/**
 * Custom implementation of {@link List} with support for the following methods:
 * add,get,remove,clear,sort,and more
 *
 * @author alexandr-khvatov
 */
public class MyArrayList<E> implements List<E> {
    private int size = 0;
    private Object[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Integer MAX_CAPACITY = Integer.MAX_VALUE - 8;

    /**
     * Constructs an empty list with a default capacity of ten.
     */
    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructs an empty list with the specified capacity.
     *
     * @param capacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.elements = new Object[capacity];
        } else if (capacity == 0) {
            this.elements = new Object[]{};
        } else {
            throw new IllegalArgumentException("initial capacity is negative: " + capacity);
        }
    }

    /**
     * Creates a list containing all elements of the collection
     *
     * @param collection the collection whose elements are to be in the list
     * @throws IllegalArgumentException if the specified collection is null
     */
    public MyArrayList(Collection<? extends E> collection) {
        if (isNull(collection)) {
            throw new IllegalArgumentException("Collection is null");
        }
        elements = collection.toArray();
        size = elements.length;
    }

    /**
     * Adds the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return true because the list is always modified as the result of this operation.
     */
    @Override
    public boolean add(E e) {
        if (elements.length > size) {
            addElement(e);
        } else {
            growUp(size + 1);
            addElement(e);
        }
        size++;
        return true;
    }

    /**
     * Inserts an element into the list at the specified [index].
     *
     * @param e element to be appended to this list
     */
    @Override
    public void add(int index, E e) {
        Objects.checkIndex(index, size);

        if (size == elements.length) {
            growUp(size + 1);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = e;
        size++;
    }

    private void growUp(int requiredCapacity) {
        var currentCapacity = elements.length;
        var newCapacity = currentCapacity + (currentCapacity / 2);
        if (newCapacity - requiredCapacity < 0) {
            newCapacity = requiredCapacity;
        }
        if (newCapacity - MAX_CAPACITY > 0) {
            newCapacity = MAX_CAPACITY;
        }
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private void addElement(E e) {
        elements[size] = e;
    }

    /**
     * Removes an element at the specified [index] from the list.
     *
     * @param index the index of the element to be removed
     * @return the element that has been removed.
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);

        var removedValue = (E) elements[index];
        removeAt(index);
        return removedValue;
    }

    /**
     * Removes a single instance of the specified element from this collection, if it is present.
     *
     * @param value Return true if the element has been successfully removed;
     *              false if it was not present in the collection.
     */
    @Override
    public boolean remove(Object value) {
        if (value == null) {
            return removeFirstNullValue();
        } else {
            return removeFirstNonNullValue(value);
        }
    }

    private boolean removeFirstNullValue() {
        for (int i = 0; i < size; i++)
            if (isNull(elements[i])) {
                removeAt(i);
                return true;
            }
        return false;
    }

    private boolean removeFirstNonNullValue(Object value) {
        if (isNull(value)) {
            throw new IllegalArgumentException("Value is null");
        }
        for (int i = 0; i < size; i++)
            if (Objects.equals(value, elements[i])) {
                removeAt(i);
                return true;
            }
        return false;
    }

    private void removeAt(int index) {
        var shift = size - index - 1;
        if (shift > 0) {
            System.arraycopy(elements, index + 1, elements, index, shift);
        }
        size--;
        elements[size] = null;
    }

    /**
     * Removes all elements from list
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Sorts this list according to the order induced by the specified {@link Comparator}.
     *
     * @param comparator the {@code Comparator} used to compare list elements.
     *                   A {@code null} value indicates that the elements'
     *                   {@linkplain Comparable natural ordering} should be used
     */
    @SuppressWarnings("unchecked")
    @Override
    public void sort(Comparator<? super E> comparator) {
        Arrays.sort((E[]) elements, 0, size, comparator);
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException
     */
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);

        return (E) elements[index];
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index index of the element to replace
     * @param e     element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException
     */
    @SuppressWarnings("unchecked")
    @Override
    public E set(int index, E e) {
        Objects.checkIndex(index, size);

        var oldValue = (E) elements[index];
        elements[index] = e;

        return oldValue;
    }

    /**
     * Returns an array containing all elements
     *
     * @return
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }
}