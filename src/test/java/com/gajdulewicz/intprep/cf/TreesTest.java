package com.gajdulewicz.intprep.cf;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.cf.Trees.hasPathWithGivenSum;
import static com.gajdulewicz.intprep.cf.Trees.isTreeSymmetric;

public class TreesTest {

    private final Trees.Tree<Integer> sym;

    public TreesTest() {
        sym = new Trees.Tree<>(2);
        sym.left = new Trees.Tree<>(3);
        sym.left.left = new Trees.Tree<>(4);
        sym.right = new Trees.Tree<>(3);
        sym.right.right = new Trees.Tree<>(4);

    }

    @Test
    public void hasPathWithGivenSumTest() {
        Truth.assertThat(hasPathWithGivenSum(sym,9)).isTrue();
    }

    @Test
    public void isTreeSymmetricTest() {
        Truth.assertThat(isTreeSymmetric(sym)).isTrue();
    }

}