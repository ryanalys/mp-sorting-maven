package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Tests of Sorter objects. Please do not use this class directly.
 * Rather, you should subclass it and initialize stringSorter and
 * intSorter in a static @BeforeAll method.
 *
 * @author Your Name
 * @uathor Samuel A. Rebelsky
 */
public class TestSorter {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * The sorter we use to sort arrays of strings.
   */
  static Sorter<String> stringSorter = null;

  /**
   * The sorter we use to sort arrays of integers.
   */
  static Sorter<Integer> intSorter = null;

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Given a sorted array and a permutation of the array, sort the
   * permutation and assert that it equals the original.
   *
   * @param <T>
   *   The type of values in the array.
   * @param sorted
   *   The sorted array.
   * @param perm
   *   The permuted sorted array.
   * @param sorter
   *   The thing to use to sort.
   */
  public <T> void assertSorts(T[] sorted, T[] perm, Sorter<? super T> sorter) {
    T[] tmp = perm.clone();
    sorter.sort(perm);
    assertArrayEquals(sorted, perm,
      () -> String.format("sort(%s) yields %s rather than %s",
          Arrays.toString(tmp), 
          Arrays.toString(perm), 
          Arrays.toString(sorted)));
  } // assertSorts

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A fake test. I've forgotten why I've included this here. Probably
   * just to make sure that some test succeeds.
   */
  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  /**
   * Ensure that an array that is already in order gets sorted correctly.
   */
  @Test
  public void orderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that an array that is ordered backwards gets sorted correctly.
   */
  @Test
  public void reverseOrderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "foxtrot", "delta", "charlie", "bravo", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that a randomly permuted version of a moderate-sized
   * array sorts correctly.
   */
  @Test 
  public void permutedIntegersTest() { 
    int SIZE = 100; 
    if (null == intSorter) { 
      return; 
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // permutedIntegers


  /**
   * Ensures an array that is one element long is sorted correctly
   */
  @Test
  public void singleElementTest() {
    Integer[] expected = {1};
    Integer[] permuted = {1};
    assertSorts(expected, permuted, intSorter);
  } //singleElementTest

  /**
   * Ensures we can sort an array that is multiple elements long
   */
  @Test
  public void multipleElementsTest() {
    Integer[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    Integer[] permuted = expected.clone();
    ArrayUtils.permute(permuted);
    assertSorts(expected, permuted, intSorter);
  } //multipleElementsTest

  /**
   * Ensures an array with two of the same elements sorts correctly
   */
  @Test
  public void hasDuplicateElement() {
    Integer[] expected = {0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    Integer[] permuted = expected.clone();
    ArrayUtils.permute(permuted);
    assertSorts(expected, permuted, intSorter);
  } //hasDuplicateElement

  /**
   * Ensures an array with multiple sets of duplicate elements sorts correctly
   */
  @Test
  public void hasMultipleDuplicateElements() {
    Integer[] expected = new Integer[21];
    int index = 0;
    for(int i = 0; i < 6; i++) {
      for(int j = 0; j < i+1; j++) {
        expected[index] = i;
        index++;
      } //for
    } //for
    Integer[] permuted = expected.clone();
    ArrayUtils.permute(permuted);
    assertSorts(expected, permuted, intSorter);
  }

  /**
   * Ensures a long array is sorted correctly
   */
  @Test
  public void veryLongArray() {
    Integer[] expected = new Integer[500];
    for(int i = 0; i < 1000; i+=2) {
      expected[i/2] = i;
    } //for
    Integer[] permuted = expected.clone();
    ArrayUtils.permute(permuted);
    assertSorts(expected, permuted, intSorter);
  } //veryLongArray
} // class TestSorter
