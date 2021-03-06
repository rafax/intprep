package com.gajdulewicz.intprep;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * Count the number of unique items in a large (larger than memory) list by sorting sublists,
 * storing them in files and merging sorted arrays. Should run with -Xmx8m
 */
public class ExternalSort {

  static int fileSort(Stream<String> reader) {
    List<File> files = batchSort(reader);
    int cnt = countNonDuplicates(files);
    return cnt;
  }

  private static int countNonDuplicates(List<File> files) {
    BufferedWriter txt = null;
    ListMultimap<Integer, BufferedReader> sortedReaders = sortedReaders(files);
    Integer start = sortedReaders.keySet().iterator().next();
    BufferedReader startMove = sortedReaders.get(start).get(0);
    sortedReaders.remove(start, startMove);
    String startLine;
    try {
      txt =
          new BufferedWriter(
              new FileWriter(File.createTempFile("output" + UUID.randomUUID(), "txt")));
      startLine = startMove.readLine();
      if (startLine != null) {
        sortedReaders.put(Integer.parseInt(startLine), startMove);
        txt.write(startLine);
        txt.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    int prev = start, cnt = 1, processed = 1;
    try {
      while (!sortedReaders.isEmpty()) {
        processed++;
        Integer min = sortedReaders.keySet().iterator().next();
        BufferedReader toMove = sortedReaders.get(min).get(0);
        sortedReaders.remove(min, toMove);
        if (min != prev) {
          cnt++;
          prev = min;
          txt.write(Integer.toString(prev));
          txt.newLine();
        }
        String next = toMove.readLine();
        if (next != null) {
          sortedReaders.put(Integer.parseInt(next), toMove);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      sortedReaders.forEach(
          (k, v) -> {
            try {
              v.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          });
      files.forEach(File::delete);
    }
    return cnt;
  }

  private static ListMultimap<Integer, BufferedReader> sortedReaders(List<File> files) {
    ListMultimap<Integer, BufferedReader> nextVal =
        MultimapBuilder.treeKeys().linkedListValues().build();
    try {
      for (File f : files) {
        BufferedReader r = new BufferedReader(new FileReader(f));
        String head = r.readLine();
        if (head != null) {
          nextVal.put(Integer.valueOf(head), r);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return nextVal;
  }

  private static List<File> batchSort(Stream<String> reader) {
    List<File> res = new ArrayList<>();
    int limit = 1000 * 1000;
    int[] mem = new int[limit];
    Iterator<String> iter = reader.iterator();
    int i = 0, chunk = 0;
    while (iter.hasNext()) {
      if (i == limit) {
        res.add(sortAndWrite(mem, chunk++));
        i = 0;
      }
      mem[i++] = Integer.parseInt(iter.next());
    }
    if (i > 0) {
      res.add(sortAndWrite(mem, chunk++));
    }
    return res;
  }

  private static File sortAndWrite(int[] mem, int chunk) {
    File out = null;
    Arrays.sort(mem);
    try {
      out = File.createTempFile(Integer.toString(chunk) + UUID.randomUUID(), ".extsort");
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(out, false))) {
        for (Integer aMem : mem) {
          bw.write(Integer.toString(aMem));
          bw.newLine();
        }
      }
      return out;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return out;
  }
}
