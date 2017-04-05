package com.gajdulewicz.intprep.ds;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
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
    public void testDeleteChildlessRoot() {
        final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int root = 5;
        tree.insert(root);
        final boolean deleted = tree.delete(root);
        assertThat(deleted).isTrue();
        assertThat(tree.size()).isEqualTo(0);
    }

    @Test
    public void testDeleteRootOneChild() {
        final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int root = 5, child = 6;
        tree.insert(root);
        tree.insert(child);
        final boolean deleted = tree.delete(root);
        assertThat(deleted).isTrue();
        assertThat(tree.size()).isEqualTo(1);
        assertThat(tree.levelOrder().get(0)).containsExactly(child);
    }

    @Test
    public void testBulkDelete() {
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
    @Ignore
    public void testDeleteRandomized() {
        List<Integer> elems = Lists.newArrayList();
        for (int i = 0; i < 1000; i++) {
            elems.add(i);
        }
        for (int iteration = 0; iteration < 10; iteration++) {
            Collections.shuffle(elems);
            final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
            for (Integer elem : elems) {
                tree.insert(elem);
                assertThat(tree.contains(elem)).isTrue();
            }
            for (int i = 2000; i < 2010; i++) {
                assertThat(tree.delete(i)).isFalse();
            }
            for (Integer i : elems) {
                assertThat(tree.contains(i)).isTrue();
                assertThat(tree.delete(i)).isTrue();
                assertThat(tree.contains(i)).isFalse();
            }
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
