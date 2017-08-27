package com.gajdulewicz.intprep.cf;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.cf.HashTables.*;

public class HashTablesTest {

  @Test
  public void possibleSumsTest() {
    Truth.assertThat(possibleSums(new int[] {10, 50, 100}, new int[] {1, 2, 1})).isEqualTo(9);
    Truth.assertThat(possibleSums(new int[] {1, 2}, new int[] {50000, 2})).isEqualTo(50004);
  }

  @Test
  public void isBitSetTest() {
    Truth.assertThat(isBitSet(10, 0)).isFalse();
    Truth.assertThat(isBitSet(10, 1)).isTrue();
    Truth.assertThat(isBitSet(10, 2)).isFalse();
    Truth.assertThat(isBitSet(10, 3)).isTrue();
  }

  @Test
  public void containsCloseNumsTest() {
    Truth.assertThat(containsCloseNums(new int[] {0, 1, 2, 3, 5, 2}, 3)).isTrue();
    Truth.assertThat(containsCloseNums(new int[] {0, 1, 2, 3, 5, 2}, 2)).isFalse();
  }

  @Test
  public void areFollowingPatternsTest() {
    Truth.assertThat(
            areFollowingPatterns(new String[] {"cat", "dog", "dog"}, new String[] {"a", "b", "b"}))
        .isTrue();
    Truth.assertThat(
            areFollowingPatterns(new String[] {"cat", "cat", "dog"}, new String[] {"a", "b", "b"}))
        .isFalse();
    Truth.assertThat(
            areFollowingPatterns(
                new String[] {"cat", "dog", "doggy"}, new String[] {"a", "b", "b"}))
        .isFalse();
  }

  @Test
  public void groupingDishesTest() {
    Truth.assertThat(
            groupingDishes(
                new String[][] {
                  {"Salad", "Tomato", "Cucumber", "Salad", "Sauce"},
                  {"Pizza", "Tomato", "Sausage", "Sauce", "Dough"},
                  {"Quesadilla", "Chicken", "Cheese", "Sauce"},
                  {"Sandwich", "Salad", "Bread", "Tomato", "Cheese"}
                }))
        .isEqualTo(
            new String[][] {
              {"Cheese", "Quesadilla", "Sandwich"},
              {"Salad", "Salad", "Sandwich"},
              {"Sauce", "Pizza", "Quesadilla", "Salad"},
              {"Tomato", "Pizza", "Salad", "Sandwich"}
            });
  }

  @Test
  public void swapLexOrderTest() {
    Truth.assertThat(swapLexOrder("abdc", new int[][] {{1, 4}, {3, 4}})).isEqualTo("dbca");
    Truth.assertThat(swapLexOrder("acxrabdz", new int[][] {{1, 3}, {6, 8}, {3, 8}, {2, 7}}))
        .isEqualTo("zdxrabca");
    Truth.assertThat(
            swapLexOrder(
                "lvvyfrbhgiyexoirhunnuejzhesylojwbyatfkrv",
                new int[][] {
                  {13, 23}, {13, 28}, {15, 20}, {24, 29}, {6, 7}, {3, 4}, {21, 30}, {2, 13},
                  {12, 15}, {19, 23}, {10, 19}, {13, 14}, {6, 16}, {17, 25}, {6, 21}, {17, 26},
                  {5, 6}, {12, 24}
                }))
        .isEqualTo("lyyvurrhgxyzvonohunlfejihesiebjwbyatfkrv");
  }
}
