package com.gajdulewicz.intprep.cf;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.cf.Trees.*;

public class TreesTest {

  private final Trees.Tree<Integer> sym;
  private final Trees.Tree<Integer> standard;

  public TreesTest() {
    sym = new Trees.Tree<>(2);
    sym.left = new Trees.Tree<>(3);
    sym.left.left = new Trees.Tree<>(4);
    sym.right = new Trees.Tree<>(3);
    sym.right.right = new Trees.Tree<>(4);

    standard = new Trees.Tree<>(1);
    standard.left = new Trees.Tree<>(2);
    standard.left.right = new Trees.Tree<>(3);
    standard.right = new Trees.Tree<>(4);
    standard.right.left = new Trees.Tree<>(5);
  }

  @Test
  public void hasPathWithGivenSumTest() {
    Truth.assertThat(hasPathWithGivenSum(sym, 9)).isTrue();
  }

  @Test
  public void isTreeSymmetricTest() {
    Truth.assertThat(isTreeSymmetric(sym)).isTrue();
  }

  @Test
  public void findProfessionTest() {
    Truth.assertThat(findProfession(3, 3)).isEqualTo("Doctor");
    Truth.assertThat(findProfession(3, 2)).isEqualTo("Doctor");
    Truth.assertThat(findProfession(3, 4)).isEqualTo("Engineer");
    Truth.assertThat(findProfession(30, 163126329)).isEqualTo("Doctor");
  }

  @Test
  public void treeDiameterTest() {
    Truth.assertThat(
            treeDiameter(
                10,
                new int[][] {
                  {2, 5}, {5, 7}, {5, 1}, {1, 9}, {1, 0}, {7, 6}, {6, 3}, {3, 8}, {8, 4}
                }))
        .isEqualTo(7);
    Truth.assertThat(treeDiameter(1, new int[0][0])).isEqualTo(0);
    Truth.assertThat(treeDiameter(2, new int[][] {{1, 0}})).isEqualTo(1);
  }

  @Test
  public void traverseTreeTest() {
    Truth.assertThat(traverseTree(standard)).asList().containsExactly(1, 2, 4, 3, 5).inOrder();
    final Tree<Integer> branch = new Tree<>(15);
    branch.left = new Tree<>(15);
    Truth.assertThat(traverseTree(branch)).asList().containsExactly(15, 15);
  }

  @Test
  public void largestValuesInTreeRowsTest() {
    Truth.assertThat(largestValuesInTreeRows(standard)).asList().containsExactly(1, 4, 5).inOrder();
  }

  @Test
  public void climbingStaircaseTest() {
    final int[][] actual = climbingStaircase(4, 2);
    final int[][] expected = new int[][] {{1, 1, 1, 1}, {1, 1, 2}, {1, 2, 1}, {2, 1, 1}, {2, 2}};
    for (int i = 0; i < actual.length; i++) {
      Truth.assertThat(actual[i]).isEqualTo(expected[i]);
    }
  }

  @Test
  public void nQueensTest() {
    Truth.assertThat(nQueens(4)).isEqualTo(new int[][] {{2, 4, 1, 3}, {3, 1, 4, 2}});
  }

  @Test
  public void findLongestSubarrayBySumTest() {
    Truth.assertThat(findLongestSubarrayBySum(12, new int[] {1, 2, 3, 7, 5}))
        .isEqualTo(new int[] {2, 4});
    Truth.assertThat(findLongestSubarrayBySum(15, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}))
        .isEqualTo(new int[] {1, 5});
    Truth.assertThat(findLongestSubarrayBySum(3, new int[] {2})).isEqualTo(new int[] {-1});
  }

  @Test
  public void productExceptSelfTest() {
    Truth.assertThat(
            productExceptSelf(
                new int[] {
                  28, 27, 11, 17, 19, 49, 19, 46, 41, 21, 1, 49, 18, 26, 16, 24, 16, 36, 19, 49, 31,
                  39, 11, 21, 29, 37, 34, 34, 6, 16, 26, 31, 6, 48, 38, 36, 26, 36, 38, 18
                },
                5040))
        .isEqualTo(0);
  }

  @Test
  public void minSubstringWithAllCharsTest() {
    Truth.assertThat(minSubstringWithAllChars("adobecodebanc", "abc")).isEqualTo("banc");
    Truth.assertThat(minSubstringWithAllChars("zqyvbfeiee", "ze")).isEqualTo("zqyvbfe");
    Truth.assertThat(minSubstringWithAllChars("ywcjorwmhwjfowgkpjxkdmjlrljhgtejidsiiqpnmsspzfyeoj", "wmlrjdsipzfoe")).isEqualTo("fowgkpjxkdmjlrljhgtejidsiiqpnmsspz");

  }
}
