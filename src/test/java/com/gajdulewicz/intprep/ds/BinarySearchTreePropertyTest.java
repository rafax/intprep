package com.gajdulewicz.intprep.ds;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by gajduler on 4/6/17.
 */
@RunWith(JUnitQuickcheck.class)
public class BinarySearchTreePropertyTest {

    @Property(trials = 10)
    public void testDeleteRandomized(List<Integer> elems) {
        final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (Integer elem : elems) {
            tree.insert(elem);
            assertThat(tree.contains(elem)).isTrue();
        }
        for (Integer i : Sets.newHashSet(elems)) {
            assertThat(tree.contains(i)).isTrue();
            assertThat(tree.delete(i)).isTrue();
            assertThat(tree.contains(i)).isFalse();
        }
    }
}
