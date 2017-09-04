package com.gajdulewicz.intprep.cf;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.cf.DP.*;

public class DPTest {
  @Test
  public void longestIncreasingSubsequenceTest() {
    Truth.assertThat(
            longestIncreasingSubsequence(
                new int[] {1, 231, 2, 4, 89, 32, 12, 234, 33, 90, 34, 100}))
        .isEqualTo(7);

    Truth.assertThat(
            longestIncreasingSubsequence(new int[] {68, 35, 1, 70, 25, 79, 59, 63, 65, 6, 46, 82}))
        .isEqualTo(6);
  }

  @Test
  public void climbingStairsTest() {
    Truth.assertThat(climbingStairs(1)).isEqualTo(1);
    Truth.assertThat(climbingStairs(38)).isEqualTo(63245986);
  }

  @Test
  public void houseRobberTest() {
    Truth.assertThat(houseRobber(new int[] {1, 1, 1})).isEqualTo(2);
    Truth.assertThat(houseRobber(new int[] {4, 1, 2, 7, 5, 3, 1})).isEqualTo(14);
  }

  @Test
  public void composeRangesTest() {
    Truth.assertThat(composeRanges(new int[] {-1, 0, 1, 2, 6, 7, 9}))
        .isEqualTo(new String[] {"-1->2", "6->7", "9"});
  }

  @Test
  public void mapDecodingTest() {
    Truth.assertThat(mapDecoding("132")).isEqualTo(2);
    Truth.assertThat(
            mapDecoding(
                "1221112111122221211221221212212212111221222212122221222112122212121212221212122221211112212212211211"))
        .isEqualTo(782204094);

    Truth.assertThat(mapDecoding("11115112112")).isEqualTo(104);
  }

  @Test
  public void maximalSquareTest() {
    Truth.assertThat(
            maximalSquare(
                new char[][] {
                  {'1', '0', '1', '1', '1'},
                  {'1', '0', '1', '1', '1'},
                  {'1', '1', '1', '1', '1'},
                  {'1', '0', '0', '1', '0'},
                  {'1', '0', '0', '1', '0'}
                }))
        .isEqualTo(9);
    Truth.assertThat(maximalSquare(new char[][] {{'0', '1'}, {'1', '0'}})).isEqualTo(1);
  }

  @Test
  public void regularExpressionMatchingTest() {
    Truth.assertThat(regularExpressionMatching("bb", "b")).isFalse();
    Truth.assertThat(regularExpressionMatching("aab", ".*")).isTrue();
    Truth.assertThat(regularExpressionMatching("aaa", "a*az")).isFalse();
    Truth.assertThat(regularExpressionMatching("aaa", "ab*ac*a")).isTrue();
  }

  @Test
  public void paintHousesTest() {
    Truth.assertThat(paintHouses(new int[][] {{1, 3, 4}, {2, 3, 3}, {3, 1, 4}})).isEqualTo(5);
  }

  @Test
  public void kpalindromeTest() {
    Truth.assertThat(kpalindrome("abrarbra", 1)).isTrue();
    Truth.assertThat(kpalindrome("adbcdbacdb", 2)).isFalse();
    Truth.assertThat(kpalindrome("wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco", 5)).isFalse();

  }
}
