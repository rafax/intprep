package com.gajdulewicz.intprep.ctci;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.truth.Truth;
import org.junit.Test;

public class RecursionTest {

  @Test
  public void stepsTest() {
    Truth.assertThat(Recursion.ways(4, new long[5])).isEqualTo(7);
    Truth.assertThat(Recursion.ways(35, new long[36])).isEqualTo(1132436852);
  }

  @Test
  public void gridWaysTest() {
    Truth.assertThat(Recursion.gridWays(new boolean[][]{{true, true}, {true, true}})).isEqualTo(2);
    Truth.assertThat(
      Recursion.gridWays(
        new boolean[][]{{true, true, true}, {false, false, true}, {true, true, true}}))
      .isEqualTo(1);
    Truth.assertThat(
      Recursion.gridWays(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}}))
      .isEqualTo(6);
  }

  @Test
  public void subsetsTest() {
    Truth.assertThat(Recursion.subsets(Lists.newArrayList("a", "b", "c")))
        .containsExactly(
            Sets.newHashSet(),
            Sets.newHashSet("a"),
            Sets.newHashSet("b"),
            Sets.newHashSet("c"),
            Sets.newHashSet("a", "b"),
            Sets.newHashSet("a", "c"),
            Sets.newHashSet("b", "c"),
            Sets.newHashSet("a", "b", "c"));
  }

  @Test
  public void permutationsTest() {
    Truth.assertThat(Recursion.permutations("ab")).containsExactly("ab", "ba");
    Truth.assertThat(Recursion.permutations("abc")).containsExactly("cab", "acb", "abc", "cba", "bca", "bac");
  }

  @Test
  public void parenPairsTest() {
    Truth.assertThat(Recursion.parenPairs(3)).containsExactly("((()))", "(()())", "(())()", "()(())", "()()()");
  }
}
