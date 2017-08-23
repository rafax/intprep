package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.gajdulewicz.intprep.ExternalSort.fileSort;

public class ExternalSortTest {
  @Test
  public void externalSortTest() {
    BufferedReader reader =
      new BufferedReader(
        new InputStreamReader(ExternalSortTest.class.getResourceAsStream("/externalsort/all.txt")));
    final int unique = fileSort(reader.lines());
    Truth.assertThat(unique).isEqualTo(995106);
  }

}