package com.gajdulewicz.intprep;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class Main {

    private static final Map<String, Solution> solutions = ImmutableMap.of(
            "external-sort", new ExternalSort(),
            "flatten-iterators", new FlattenIterators()
    );

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide a solution name");
            return;
        }
        String solutionName = args[0];
        if (!solutions.containsKey(solutionName)) {
            System.out.println(String.format("Unknown solution name %s, use one of:g\n%s", solutionName, String.join("\n", solutions.keySet())));
            return;
        }
        solutions.get(solutionName).solve();
    }
}

