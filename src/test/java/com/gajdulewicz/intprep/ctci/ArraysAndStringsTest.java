package com.gajdulewicz.intprep.ctci;

import com.google.common.collect.Lists;
import com.google.common.truth.Truth;
import org.junit.Test;

import java.util.function.Supplier;

import static com.gajdulewicz.intprep.ctci.ArraysAndStrings.allUnique;
import static com.gajdulewicz.intprep.ctci.ArraysAndStrings.compress;
import static com.gajdulewicz.intprep.ctci.ArraysAndStrings.replaceSpaces;

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

  static class Case<TIn, TOut> {
    private final Supplier<TIn> inSupplier;
    private final Supplier<TOut> outSupplier;

    public TIn in() {
      return inSupplier.get();
    }

    public TOut out() {
      return outSupplier.get();
    }

    Case(Supplier<TIn> inSupplier, Supplier<TOut> outSupplier) {

      this.inSupplier = inSupplier;
      this.outSupplier = outSupplier;
    }
  }
}
