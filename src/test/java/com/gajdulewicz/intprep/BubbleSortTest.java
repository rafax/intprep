package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.BubbleSort.bubbleSort;

public class BubbleSortTest {

    @Test
    public void testBubbleSort() {
        int[] arr = new int[]{3, 2, 1};
        final int swaps = bubbleSort(arr);
        Truth.assertThat(arr).asList().containsExactly(1, 2, 3);
        Truth.assertThat(swaps).isEqualTo(3);
    }

}