package com.gajdulewicz.intprep;

import com.google.common.collect.Lists;
import com.google.common.truth.Truth;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PathBfsTest {
    @Test
    public void sample() throws Exception {
        Truth.assertThat(PathBfs.findPaths(new ByteArrayInputStream(("2\n" +
                "4 2\n" +
                "1 2\n" +
                "1 3\n" +
                "1\n" +
                "3 1\n" +
                "2 3\n" +
                "2").getBytes()))).isEqualTo(Lists.newArrayList(Lists.newArrayList(6, 6, -1), Lists.newArrayList(-1, 6)));
    }

    @Test
    public void sample2() throws Exception {
        final InputStream source = this.getClass().getResourceAsStream("/bfs/case2.txt");
        final Scanner sol = new Scanner(this.getClass().getResourceAsStream("/bfs/case2_sol.txt"));
        final List<List<Integer>> paths = PathBfs.findPaths(source);
        int i = 0;
        while (sol.hasNextLine()) {
            Truth.assertThat(Arrays.stream(sol.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList())).isEqualTo(paths.get(i++));
        }
    }

}