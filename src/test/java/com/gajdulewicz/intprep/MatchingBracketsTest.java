package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.MatchingBrackets.isBalanced;

/** Created by gajduler on 7/21/17. */
public class MatchingBracketsTest {
  @Test
  public void isBalancedReturnsTrueForOnePair() {
    Truth.assertThat(isBalanced("{}")).isTrue();
  }

  @Test
  public void isBalancedReturnsTrueForThreeNestedPairs() {
    Truth.assertThat(isBalanced("{[()]}")).isTrue();
  }

  @Test
  public void isBalancedReturnsFalseForOnlyLeft() {
    Truth.assertThat(isBalanced("{}}")).isFalse();
  }
}
