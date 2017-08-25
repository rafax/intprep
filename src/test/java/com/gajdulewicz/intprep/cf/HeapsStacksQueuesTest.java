package com.gajdulewicz.intprep.cf;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.cf.HeapsStacksQueues.decodeString;
import static com.gajdulewicz.intprep.cf.HeapsStacksQueues.kthLargestElement;
import static com.gajdulewicz.intprep.cf.HeapsStacksQueues.simplifyPath;

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


}