package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

/**
 * Test of RyanAlyssaSort
 * @author Alyssa Ryan
 * @author Sam Rebelsky
 */
public class TestRyanAlyssaSort extends TestSorter{
  /**
   * Sets up the sorter
   * Mainly taken from code created by Sam Rebelsky
   */
  @BeforeAll
  static void setup() {
    stringSorter = new RyanAlyssaSort<String>((x,y) -> x.compareTo(y));
    intSorter = new RyanAlyssaSort<Integer>((x,y) -> x.compareTo(y));
  } // setup()
} //TestRyanAlyssaSort
