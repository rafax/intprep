package com.gajdulewicz.intprep.cf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Trees {
    static class Tree<T> {
        Tree(T x) {
            value = x;
        }

        T value;
        Tree<T> left;
        Tree<T> right;
    }

    static boolean hasPathWithGivenSum(Tree<Integer> t, int s) {
        if (t == null && s == 0) {
            return true;
        }
        return hasPath(t, s, 0);
    }

    static private boolean hasPath(Tree<Integer> t, int s, int sum) {
        if (t == null) {
            return false;
        }
        if (t.left == null && t.right == null) {
            if (sum + t.value == s) {
                return true;
            }
        }
        return hasPath(t.left, s, sum + t.value) || hasPath(t.right, s, sum + t.value);
    }

    static boolean isTreeSymmetric(Tree<Integer> t) {
        if (t == null) return true;
        if ((t.left == null && t.right != null) || (t.left != null && t.right == null) || (t.left != null && t.right != null && !Objects.equals(t.left.value, t.right.value)))
            return false;
        List<Integer> lOrder = new ArrayList<>();
        List<Integer> rOrder = new ArrayList<>();
        inOrder(t.left, lOrder);
        inOrder(t.right, rOrder);
        Collections.reverse(rOrder);
        return lOrder.equals(rOrder);
    }

    static void inOrder(Tree<Integer> t, List<Integer> order) {
        if (t == null) return;
        inOrder(t.left, order);
        order.add(t.value);
        inOrder(t.right, order);
    }


}
