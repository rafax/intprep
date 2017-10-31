package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.gajdulewicz.intprep.DateIterator.iterate;

public class DateIteratorTest {
  @Test
  public void iterateTest() {
    List<DateIterator.Date> actual = new ArrayList<>();
    final Consumer<DateIterator.Date> consumer = actual::add;
    final DateIterator.Date start = new DateIterator.Date(-1, 12, 31);
    iterate(start, new DateIterator.Date(1, 1, 2), consumer);
    Truth.assertThat(actual).containsExactly(start, new DateIterator.Date(1, 1, 1)).inOrder();
  }
}
