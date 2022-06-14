package util;

import java.util.Comparator;

/**
 * Resizable-array implementation of the {@link util.List} interface. Implements
 * all list operations, and permits all elements, including null. The {@code size},
 * {@code isEmpty} and {@code get} operations run in constant time. The {@code add},
 * {@code delete} and {@code contains} operation runs in constant time (O(n)).
 * Each ArrayList instance has a capacity. The capacity is the size of the array used to store
 * the elements in the list. It is always at least as large as the list size. As elements are added
 * to an ArrayList, its capacity grows automatically.
 *
 * @param <E> the type of elements in this list
 * @author Fulkin
 * @see util.List
 */
public class ArrayList<E> implements List<E> {

    /**
     * Default initial capacity
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer.
     */
    private Object[] elementData;

    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    private int nElems;

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public ArrayList() {
        elementData = new Object[DEFAULT_SIZE];
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param capacity - the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public ArrayList(int capacity) {
        if (capacity >= 0) {
            elementData = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element - element to be added to the end of this list
     */
    @Override
    public void add(E element) {
        if (nElems == elementData.length) {
            grow();
        }
        elementData[nElems] = element;
        nElems++;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index   - the index at which the specified element should be inserted
     * @param element - the element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    @Override
    public void add(int index, E element) {
        checkIndexOutBounds(index);
        if (nElems == elementData.length) {
            grow();
        }

        System.arraycopy(elementData, index,
                elementData, index + 1,
                nElems - index);
        elementData[index] = element;
        nElems++;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index - index of the element to return returns
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    @Override
    public E get(int index) {
        checkIndexOutBounds(index);
        return elementData(index);
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present. If the list does not contain the element, it is unchanged.
     *
     * @param element - element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
    @Override
    public boolean delete(E element) {
        int i;
        for (i = 0; i < nElems; i++) {
            if (element.equals(elementData(i))) {
                break;
            }
        }
        if (i == nElems) {
            return false;
        } else {
            removeAndShifts(i);
            return true;
        }
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left.
     *
     * @param index - the element previously at the specified position
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    @Override
    public E delete(int index) {
        checkIndexOutBounds(index);
        E deleteData = elementData(index);
        removeAndShifts(index);
        return deleteData;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param element element whose presence in this list is to be checked
     * @return {@code true} if this list contains the specified element.
     * If there are no items in the list or if the element is {@code null}
     * it returns {@code false}
     */
    @Override
    public boolean contains(E element) {
        if (isEmpty() || element == null) {
            return false;
        }
        for (int i = 0; i < nElems; i++) {
            if (element.equals(elementData(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return nElems;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return nElems == 0;
    }

    /**
     * Sorts this list according to the order induced by the specified Comparator.
     * All elements in this list must be mutually comparable using the specified
     * comparator (that is, c.compare(e1, e2) must not throw a ClassCastException
     * for any elements e1 and e2 in the list). If the specified comparator is null
     * then all elements in this list must implement the Comparable interface and
     * the elements' natural ordering should be used. Does nothing when the list is empty.
     *
     * @param c - the Comparator used to compare list elements.
     *          A null value indicates that the elements' natural ordering should be used
     * @throws ClassCastException       if the list contains elements that are not
     *                                  mutually comparable using the specified comparator
     * @throws IllegalArgumentException if the {@code Comparator} is null and
     *                                  if the objects do not implement {@code Comparable}
     */
    @Override
    public void sort(Comparator<? super E> c) {
        if (isEmpty()) {
            return;
        }
        if (c == null) {
            sort();
        }
        quickSortComparator(0, nElems - 1, c);
    }

    /**
     * Sorts this list according to the natural order. All elements in this list
     * must implement the Comparable interface. Does nothing when the list is empty.
     *
     * @throws ClassCastException       if the list contains elements that are not
     *                                  mutually comparable using the specified comparator
     * @throws IllegalArgumentException if the objects do not implement {@code Comparable}
     */
    @Override
    public void sort() {
        if (isEmpty()) {
            return;
        }
        if (elementData(0) instanceof Comparable) {
            quickSortComparable(0, nElems - 1);
        } else {
            throw new IllegalArgumentException("For sorting the type must implement Comparable");
        }
    }


    /*
      private methods
     */

    @SuppressWarnings("unchecked")
    private <E> E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * Increases the capacity.
     */
    private void grow() {
        int newCapacity = (elementData.length * 3) / 2 + 1;
        E[] newArray = (E[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, nElems);
        elementData = newArray;
    }

    /**
     * Moves the entire array to the left by one element, starting with the element to be removed
     *
     * @param i - index of the element to be removed
     */
    private void removeAndShifts(int i) {
        int newSize;
        if ((newSize = nElems - 1) > i) {
            System.arraycopy(elementData, i + 1, elementData, i, newSize - i);
            elementData[nElems = newSize] = null;
        }
    }

    /**
     * Throws an exception if the condition is not met.
     *
     * @param index - index to check
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    private void checkIndexOutBounds(int index) {
        if (index > nElems || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + nElems);
        }
    }

    /**
     * Quicksort implementation for natural order.
     *
     * @param start - the left element of the subarray
     * @param end - the right element of the subarray
     * @param <E> - the type of elements in this list
     */
    private <E extends Comparable<? super E>> void quickSortComparable(int start, int end) {
        if (isEmpty() || start >= end) {
            return;
        }

        int centre = start + (end - start) / 2;

        E elementCentre = elementData(centre);
        int i = start, j = end;
        while (i <= j) {
            while (elementCentre.compareTo(elementData(i)) > 0) {
                i++;
            }
            while (elementCentre.compareTo(elementData(j)) < 0) {
                j--;
            }
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        if (start < j) {
            quickSortComparable(start, j);
        }
        if (end > i) {
            quickSortComparable(i, end);
        }
    }

    /**
     *
     * Quicksort implementation with Comparator.
     *
     * @param start - the left element of the subarray
     * @param end - the right element of the subarray
     * @param c - Comparator that is used in element comparison
     */
    private void quickSortComparator(int start, int end, Comparator<? super E> c) {
        if (start >= end) {
            return;
        }

        int centre = start + (end - start) / 2;

        E elementCentre = elementData(centre);
        int i = start, j = end;
        while (i <= j) {
            while (c.compare(elementCentre, elementData(i)) > 0) {
                i++;
            }
            while (c.compare(elementCentre, elementData(j)) < 0) {
                j--;
            }
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        if (start < j) {
            quickSortComparator(start, j, c);
        }
        if (end > i) {
            quickSortComparator(i, end, c);
        }
    }

    /**
     * changing each other's indexes for sorting
     *
     * @param i - index of first element
     * @param j - index of second element
     * @param <E> - the type of elements in this list
     */
    private <E> void swap(int i, int j) {
        E tmp = elementData(i);
        elementData[i] = elementData(j);
        elementData[j] = tmp;
    }
}
