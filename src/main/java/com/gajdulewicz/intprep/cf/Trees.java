package com.gajdulewicz.intprep.cf;

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

}
