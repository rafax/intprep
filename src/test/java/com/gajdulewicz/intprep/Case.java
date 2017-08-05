package com.gajdulewicz.intprep;

import java.util.function.Supplier;

public class Case<TIn, TOut> {
  private final Supplier<TIn> inSupplier;
  private final Supplier<TOut> outSupplier;

  public TIn in() {
    return inSupplier.get();
  }

  public TOut out() {
    return outSupplier.get();
  }

  public Case(Supplier<TIn> inSupplier, Supplier<TOut> outSupplier) {
    this.inSupplier = inSupplier;
    this.outSupplier = outSupplier;
  }

  public Case(TIn in, TOut out) {
    this.inSupplier = () -> in;
    this.outSupplier = () -> out;
  }
}
