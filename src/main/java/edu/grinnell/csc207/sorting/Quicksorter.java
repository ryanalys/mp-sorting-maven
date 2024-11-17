package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class Quicksorter<T> implements Sorter<T> {
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
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
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
      Random rand = new Random();
      int pivot_num = rand.nextInt(values.length);
      T pivot = values[pivot_num];
      int mid = (values.length) / 2;
      T[] leftArr = Arrays.copyOfRange(values, 0, mid);
      T[] rightArr = Arrays.copyOfRange(values, mid, values.length);

      int left = 0;
      int right = 0;
      int index = 0;

      while(index < values.length && left < leftArr.length && right < rightArr.length) {
        if(order.compare(values[index], pivot) < 0) {
          leftArr[left++] = values[index++];
        } else {
          rightArr[right++] = values[index++];
        } //if/else
      } //while

      while(left < leftArr.length && index < values.length) {
        leftArr[left++] = values[index++];
      } //while

      while(right < rightArr.length && index < values.length) {
        rightArr[right++] = values[index++];
      } //while

      sort(leftArr);
      sort(rightArr);

      left = 0;
      right = 0;
      index = 0;

      while(left < leftArr.length && right<rightArr.length) {
        if(order.compare(leftArr[left], rightArr[right]) < 0) {
          values[index++] = leftArr[left++];
        } else {
          values[index++] = rightArr[right++];
       } //if/else
      } //while

      while(left < leftArr.length) {
        values[index++] = leftArr[left++];
      } //while

      while(right < rightArr.length) {
        values[index++] = rightArr[right++];
      } //while
    } //if
  } // sort(T[])
} // class Quicksorter
