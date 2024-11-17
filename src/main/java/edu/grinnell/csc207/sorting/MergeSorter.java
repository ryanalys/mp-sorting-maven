package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Arrays;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    if(values.length > 1) {
      int mid = (values.length) / 2;
      T[] leftArr = Arrays.copyOfRange(values, 0, mid);
      T[] rightArr = Arrays.copyOfRange(values, mid, values.length);
      
      sort(leftArr);
      sort(rightArr);

      int left = 0;
      int right = 0;
      int index = 0;

      while(left < leftArr.length && right<rightArr.length) {
        if(order.compare(leftArr[left], rightArr[right]) < 0) {
          values[index++] = leftArr[left++];
        } else {
          values[index++] = rightArr[right++];
       } //if/else
      }

      while(left < leftArr.length) {
        values[index++] = leftArr[left++];
      }

      while(right < rightArr.length) {
        values[index++] = rightArr[right++];
      }
    } //if
  } // sort(T[])
} // class MergeSorter
