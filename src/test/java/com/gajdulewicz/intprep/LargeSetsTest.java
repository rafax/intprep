package com.gajdulewicz.intprep;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.truth.Truth.assertThat;

public class LargeSetsTest {
    @Test
    public void testIntersection() {
        final List<String> common = LargeSets.intersect("/large_sets/list_a.txt", "/large_sets/list_b.txt").collect(Collectors.toList());
        assertThat(common.size()).isEqualTo(5 * 1000 * 1000);
    }
}
