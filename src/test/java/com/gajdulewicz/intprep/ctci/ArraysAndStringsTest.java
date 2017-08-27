package com.gajdulewicz.intprep.ctci;

import com.gajdulewicz.intprep.Case;
import com.google.common.collect.Lists;
import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.ctci.ArraysAndStrings.*;

public class ArraysAndStringsTest {
  @Test
  public void allUniqueTest() {
    Lists.newArrayList(
            new Case<>(() -> "abcd", () -> true),
            new Case<>(() -> "abba", () -> false),
            new Case<>(() -> "a", () -> true),
            new Case<>(() -> "", () -> true))
        .forEach(c -> Truth.assertThat(allUnique(c.in())).isEqualTo(c.out()));
  }

  @Test
  public void replaceSpacesTest() {
    Lists.newArrayList(
            new Case<>("He is tall    "::toCharArray, "He%20is%20tall"::toCharArray),
            new Case<>("Dat guy  "::toCharArray, "Dat%20guy"::toCharArray),
            new Case<>("Datguy"::toCharArray, "Datguy"::toCharArray),
            new Case<>(""::toCharArray, ""::toCharArray))
        .forEach(
            c -> {
              final char[] in = c.in();
              replaceSpaces(in);
              Truth.assertThat(in).isEqualTo(c.out());
            });
  }

  @Test
  public void compressTest() {
    Lists.newArrayList(
            new Case<>(() -> "super", () -> "super"),
            new Case<>(() -> "aabcccccaaa", () -> "a2b1c5a3"),
            new Case<>(() -> "aaa", () -> "a3"))
        .forEach(c -> Truth.assertThat(compress(c.in())).isEqualTo(c.out()));
  }
}
