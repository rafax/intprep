package com.gajdulewicz.intprep.cf;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.cf.Arrays.*;

public class ArraysTest {
  @Test
  public void sudoku2Test() {
    Truth.assertThat(sudoku2(new char[][]{{'.', '.', '.', '1', '4', '.', '.', '2', '.'},
      {'.', '.', '6', '.', '.', '.', '.', '.', '.'},
      {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
      {'.', '.', '1', '.', '.', '.', '.', '.', '.'},
      {'.', '6', '7', '.', '.', '.', '.', '.', '9'},
      {'.', '.', '.', '.', '.', '.', '8', '1', '.'},
      {'.', '3', '.', '.', '.', '.', '.', '.', '6'},
      {'.', '.', '.', '.', '.', '7', '.', '.', '.'},
      {'.', '.', '.', '5', '.', '.', '.', '7', '.'}})).isTrue();
    Truth.assertThat(sudoku2(new char[][]{{'.', '.', '.', '.', '2', '.', '.', '9', '.'},
      {'.', '.', '.', '.', '6', '.', '.', '.', '.'},
      {'7', '1', '.', '.', '7', '5', '.', '.', '.'},
      {'.', '7', '.', '.', '.', '.', '.', '.', '.'},
      {'.', '.', '.', '.', '8', '3', '.', '.', '.'},
      {'.', '.', '8', '.', '.', '7', '.', '6', '.'},
      {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
      {'.', '1', '.', '2', '.', '.', '.', '.', '.'},
      {'.', '2', '.', '.', '3', '.', '.', '.', '.'}})).isFalse();
    Truth.assertThat(sudoku2(new char[][]{
      {'.', '.', '.', '.', '2', '.', '.', '9', '.'},
      {'.', '.', '1', '.', '6', '.', '.', '.', '.'},
      {'7', '1', '.', '.', '7', '5', '.', '.', '.'},
      {'.', '7', '.', '.', '.', '.', '.', '.', '.'},
      {'.', '.', '.', '.', '8', '3', '.', '.', '.'},
      {'.', '.', '8', '.', '.', '7', '.', '6', '.'},
      {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
      {'.', '5', '.', '2', '.', '.', '.', '.', '.'},
      {'.', '2', '.', '.', '3', '.', '.', '.', '.'}})).isFalse();
  }

  @Test
  public void firstDuplicateTest() {
    Truth.assertThat(firstDuplicate(new int[]{1, 2, 3, 2, 3})).isEqualTo(2);
  }

  @Test
  public void firstNonDuplicateTest() {
    Truth.assertThat(firstNotRepeatingCharacter("aabbcd")).isEqualTo('c');
  }

  @Test
  public void rotateImageTest() {
    Truth.assertThat(rotateImage(new int[][]{{1, 2, 3},
      {4, 5, 6},
      {7, 8, 9}})).isEqualTo(new int[][]{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}
    });
  }

  @Test
  public void transposeTest() {
    int[][] a = new int[][]{
      {1, 2, 3},
      {4, 5, 6},
      {7, 8, 9}};
    transpose(a);
    Truth.assertThat(a).isEqualTo(new int[][]{
      {1, 4, 7},
      {2, 5, 8},
      {3, 6, 9}
    });
  }

}