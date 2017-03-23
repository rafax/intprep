package com.gajdulewicz.intprep;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class Main {

    public interface Solution {
        void solve();
    }

    private static final Map<String, Solution> solutions = ImmutableMap.of(
            "external-sort", new ExternalSort(),
            "flatten-iterators", new FlattenIterators()
    );

    public static void main(String[] args) {
        solutions.get(args[0]).solve();
    }
}

