package com.gajdulewicz.intprep.ctci;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.ctci.Recursion.*;

public class RecursionTest {

  @Test
  public void stepsTest() {
    Truth.assertThat(ways(4, new long[5])).isEqualTo(7);
    Truth.assertThat(ways(35, new long[36])).isEqualTo(1132436852);
  }

  @Test
  public void gridWaysTest() {
    Truth.assertThat(gridWays(new boolean[][]{{true, true}, {true, true}})).isEqualTo(2);
    Truth.assertThat(
      gridWays(
        new boolean[][]{{true, true, true}, {false, false, true}, {true, true, true}}))
      .isEqualTo(1);
    Truth.assertThat(
      gridWays(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}}))
      .isEqualTo(6);
  }

  @Test
  public void subsetsTest() {
    Truth.assertThat(subsets(Lists.newArrayList("a", "b", "c")))
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
    Truth.assertThat(permutations("ab")).containsExactly("ab", "ba");
    Truth.assertThat(permutations("abc")).containsExactly("cab", "acb", "abc", "cba", "bca", "bac");
  }

  @Test
  public void parenPairsTest() {
    Truth.assertThat(parenPairs(3)).containsExactly("((()))", "(()())", "(())()", "()(())", "()()()");
  }
}
