package util;

import java.util.Comparator;

/**
 * This class contains method for sorting arrays.
 */
public class ArraysUtil {

    /**
     * Suppresses default constructor.
     */
    private ArraysUtil() {
    }

    /**
     * Sorts the received array by comparator. If (comparator == null) sorts elements
     * in natural order using quick sort.
     *
     * @param array - array to be sorted
     * @param from  - sort from what index in the array
     * @param to    - sort to what index in the array
     * @param c     - the Comparator used to compare list elements.
     *              A null value indicates that the elements' natural ordering should be used,
     *              may be null
     * @param <E>   - type that must implement Comparable interface if (c == null),
     *              otherwise throws {@code ClassCastException}
     * @throws ClassCastException if the list contains elements that are not mutually comparable using
     *                            the specified comparator or do not implement {@code Comparable} interface
     */
    public static <E> void sort(E[] array, int from, int to, Comparator<? super E> c) {
        if (c == null) {
            quickSortComparable(array, from, to);
        } else {
            quickSortComparator(array, from, to, c);
        }
    }

    /**
     * Quicksort implementation for natural order.
     *
     * @param start - the left element of the subarray
     * @param end   - the right element of the subarray
     * @param <E>   - type that must implement Comparable interface,
     *              otherwise throws {@code ClassCastException}
     */
    private static <E extends Comparable<? super E>> void quickSortComparable(Object[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int centre = start + (end - start) / 2;

        Object elementCentre = array[centre];
        int i = start, j = end;
        while (i <= j) {
            while (((Comparable) elementCentre).compareTo(array[i]) > 0) {
                i++;
            }
            while (((Comparable) elementCentre).compareTo(array[j]) < 0) {
                j--;
            }
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        if (start < j) {
            quickSortComparable(array, start, j);
        }
        if (end > i) {
            quickSortComparable(array, i, end);
        }
    }

    /**
     * Quicksort implementation with Comparator.
     *
     * @param start - the left element of the subarray
     * @param end   - the right element of the subarray
     * @param c     - Comparator that is used in element comparison
     */
    private static <E> void quickSortComparator(E[] array, int start, int end, Comparator<? super E> c) {
        if (start >= end) {
            return;
        }

        int centre = start + (end - start) / 2;

        E elementCentre = array[centre];
        int i = start, j = end;
        while (i <= j) {
            while (c.compare(elementCentre, array[i]) > 0) {
                i++;
            }
            while (c.compare(elementCentre, array[j]) < 0) {
                j--;
            }
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        if (start < j) {
            quickSortComparator(array, start, j, c);
        }
        if (end > i) {
            quickSortComparator(array, i, end, c);
        }
    }

    /**
     * swaps a[i] with a[j]
     */
    private static void swap(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
