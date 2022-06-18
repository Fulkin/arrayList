package util;

import java.util.Comparator;

/**
 * A list to access elements by their integer index. The list is also used
 * to find an element in the list or get it by index. The user can add
 * an element to any place in the list by index. List allows you
 * to use elements of any type.
 *
 * @param <E> the type of elements in this list
 * @author Fulkin
 */
public interface List<E> {

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element - element to be added to the end of this list
     * @throws ClassCastException if the class of the specified element
     *                            prevents it from being added to this list
     */
    void add(E element);

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at this position (if any) and any subsequent
     * elements to the right.
     *
     * @param index   - the index at which the specified element should be inserted
     * @param element - the element to be inserted
     * @throws ClassCastException        if the class of the specified element
     *                                   prevents it from being added to this list
     * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index > size()})
     */
    void add(int index, E element);

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index - index of the element to return returns
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException - if the index is out of range ({@code index < 0 || index > size()})
     */
    E get(int index);

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * If this list does not contain the element, it is unchanged. Returns true if this list
     * contained the specified element.
     *
     * @param element - element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     */
    boolean delete(E element);

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left. Returns the element that was removed from the list.
     *
     * @param index - the element previously at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    E delete(int index);

    /**
     * Returns true if this list contains the specified element.
     *
     * @param element element whose presence in this list is to be checked
     * @return {@code true} if this list contains the specified element.
     * If there are no items in the list or if the element is {@code null}
     * it returns {@code false}
     * @throws ClassCastException if the type of the specified element
     *                            is incompatible with this list
     */
    boolean contains(E element);

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    int size();

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    boolean isEmpty();

    /**
     * All elements in this list must be mutually comparable using the specified comparator
     * (that is, c.compare(e1, e2) must not throw a ClassCastException for any elements
     * e1 and e2 in the list). If the specified comparator is null then all elements in this
     * list must implement the Comparable interface otherwise throw {@code IllegalArgumentException}.
     * Does nothing when the list is empty.
     *
     * @param c - the Comparator used to compare list elements.
     *          A null value indicates that the elements' natural ordering should be used,
     *          may be {@code null}
     * @throws ClassCastException if the list contains elements that are not mutually comparable using
     *                            the specified comparator or do not implement {@code Comparable} interface
     */
    void sort(Comparator<? super E> c);
}
