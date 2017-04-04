package com.gajdulewicz.intprep.ds;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by gajduler on 4/4/17.
 */
public class BinarySearchTreeTest {
    @Test
    public void testFindAfterInsert() {
        final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }
        for (int i = 0; i < 10; i++) {
            assertThat(tree.contains(i)).isTrue();
        }
        for (int i = 100; i < 110; i++) {
            assertThat(tree.contains(i)).isFalse();
        }
    }

    @Test
    public void testDelete() {
        final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < 10; i++) {
            tree.insert(i);
            assertThat(tree.contains(i)).isTrue();
        }
        for (int i = 100; i < 110; i++) {
            assertThat(tree.delete(i)).isFalse();
        }
        for (int i = 0; i < 10; i++) {
            assertThat(tree.contains(i)).isTrue();
            assertThat(tree.delete(i)).isTrue();
            assertThat(tree.contains(i)).isFalse();
        }
    }

    @Test
    public void testLevelOrder() {
        final ArrayList<Integer> expected = Lists.newArrayList(5, 3, 7, 4, 2, 6, 8);
        final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (Integer v : expected) {
            tree.insert(v);
        }
        final List<List<Integer>> levels = tree.levelOrder();
        assertThat(levels).hasSize(3);
        assertThat(levels.get(0)).containsExactly(5);
        assertThat(levels.get(1)).containsExactly(3, 7);
        assertThat(levels.get(2)).containsExactly(2, 4, 6, 8);
    }
}
