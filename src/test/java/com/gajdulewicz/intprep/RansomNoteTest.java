package com.gajdulewicz.intprep;

import com.google.common.collect.Lists;
import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.RansomNote.canCreateNote;

public class RansomNoteTest {

  @Test
  public void positive() {
    Truth.assertThat(
            canCreateNote(
                Lists.newArrayList("give", "me", "one", "grand", "today", "night"),
                Lists.newArrayList("give", "one", "grand", "today")))
        .isTrue();
  }

  @Test
  public void negative() {
    Truth.assertThat(
            canCreateNote(
                Lists.newArrayList("two", "times", "three", "is", "not", "four"),
                Lists.newArrayList("two", "times", "two", "is", "four")))
        .isFalse();
  }
}
