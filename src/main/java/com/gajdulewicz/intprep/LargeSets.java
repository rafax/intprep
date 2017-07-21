package com.gajdulewicz.intprep;

import com.google.common.collect.ImmutableSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by gajduler on 4/14/17.
 */
public class LargeSets {

    public static Stream<String> intersect(String file1, String file2) {
        final Set<String> a = toSet(file1);
        System.out.println("Loaded 1");
        return getLines(file2).filter(a::contains);
    }

    private static ImmutableSet<String> toSet(String file) {
        return getLines(file).collect(ImmutableSet.toImmutableSet());
    }

    private static Stream<String> getLines(String file) {
        return new BufferedReader(new InputStreamReader(LargeSets.class.getResourceAsStream(file))).lines();
    }
}
