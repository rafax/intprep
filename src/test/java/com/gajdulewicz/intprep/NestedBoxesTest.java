package com.gajdulewicz.intprep;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class NestedBoxesTest {

  List<Integer> sample =
      Lists.newArrayList(100, 100, 99, 40, 98, 98, 97, 97, 4, 4, 3, 1, 3, 3, 2, 2, 1, 1);

  @Test
  public void testConvertsAllDimensions() {
    final NestedBoxes pb = new NestedBoxes(sample);
    assertThat(pb).isNotNull();
    assertThat(pb.getBoxCount()).isSameAs(9);
  }

  @Test
  public void testCalculatesChain() {
    final NestedBoxes pb = new NestedBoxes(sample);
    assertThat(pb).isNotNull();
    assertThat(pb.maxContainedBoxes()).isEqualTo(7);
    assertThat(pb.maxContainedBoxesGreedy()).isEqualTo(7);
  }

  @Test
  public void testNoContainmentMeansOneBox() {
    final NestedBoxes pb = new NestedBoxes(Lists.newArrayList(3, 1, 2, 2));
    assertThat(pb).isNotNull();
    assertThat(pb.maxContainedBoxes()).isEqualTo(1);
    assertThat(pb.maxContainedBoxesGreedy()).isEqualTo(1);
  }

  @Test
  public void testBothLarger() {
    assertThat(new NestedBoxes.Box(100, 100).canContain(new NestedBoxes.Box(1, 1))).isTrue();
  }

  @Test
  public void testBothSmaller() {
    assertThat(new NestedBoxes.Box(1, 1).canContain(new NestedBoxes.Box(100, 100))).isFalse();
  }

  @Test
  public void testEqual() {
    assertThat(new NestedBoxes.Box(2, 2).canContain(new NestedBoxes.Box(2, 2))).isFalse();
  }

  @Test
  public void testWidthEqual() {
    assertThat(new NestedBoxes.Box(3, 2).canContain(new NestedBoxes.Box(3, 1))).isFalse();
  }

  @Test
  public void testWidthEqualHeight() {
    assertThat(new NestedBoxes.Box(2, 3).canContain(new NestedBoxes.Box(3, 1))).isFalse();
  }

  @Test
  public void testHeightEqual() {
    assertThat(new NestedBoxes.Box(2, 3).canContain(new NestedBoxes.Box(1, 3))).isFalse();
  }

  @Test
  public void testOneEqual() {
    assertThat(new NestedBoxes.Box(2, 3).canContain(new NestedBoxes.Box(3, 4))).isFalse();
  }

  @Test
  public void testOneSmaller() {
    assertThat(new NestedBoxes.Box(2, 3).canContain(new NestedBoxes.Box(3, 3))).isFalse();
  }

  @Test
  public void testRotated() {
    assertThat(new NestedBoxes.Box(2, 3).canContain(new NestedBoxes.Box(2, 1))).isTrue();
  }
}
