package com.gajdulewicz.intprep.cf;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.cf.HeapsStacksQueues.*;

public class HeapsStacksQueuesTest {

  @Test
  public void kthLargestTest() {
    Truth.assertThat(kthLargestElement(new int[]{7, 6, 5, 4, 3, 2, 1}, 2)).isEqualTo(6);
  }

  @Test
  public void simplifyPathTest() {
    Truth.assertThat(simplifyPath("/home/a/./x/../b//c/")).isEqualTo("/home/a/b/c");
    Truth.assertThat(simplifyPath("//a//b//./././c")).isEqualTo("/a/b/c");
  }

  @Test
  public void decompressTest() {
    Truth.assertThat(decodeString("4[ab]")).isEqualTo("abababab");
    Truth.assertThat(decodeString("100[codefights]")).isEqualTo("codefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefightscodefights");

    Truth.assertThat(decodeString("2[b3[a]]")).isEqualTo("baaabaaa");

  }

  @Test
  public void nextLargerTest() {
    Truth.assertThat(nextLarger(new int[]{6, 7, 3, 8})).isEqualTo(new int[]{7, 8, 8, -1});

    Truth.assertThat(nextLarger(new int[]{6, 2, 7, 3, 1, 0, 4, 5})).isEqualTo(new int[]{7, 7, -1, 4, 4, 4, 5, -1});
  }

  @Test
  public void minimumOnStackTest() {
    Truth.assertThat(minimumOnStack(new String[]{"push 10", "min", "push 5", "min", "push 8", "min", "pop", "min", "pop", "min"})).isEqualTo(new int[]{10, 5, 5, 5, 10});
  }

  @Test
  public void countCloudsTest() {
    Truth.assertThat(countClouds(new char[][]{
      {'0', '1', '1', '0', '1'},
      {'0', '1', '1', '1', '1'},
      {'0', '0', '0', '0', '1'},
      {'1', '0', '0', '1', '1'}})).isEqualTo(2);
    Truth.assertThat(countClouds(new char[][]{{'0', '1', '0', '0', '1'},
      {'1', '1', '0', '0', '0'},
      {'0', '0', '1', '0', '1'},
      {'0', '0', '1', '1', '0'},
      {'1', '0', '1', '1', '0'}})).isEqualTo(5);
  }

}