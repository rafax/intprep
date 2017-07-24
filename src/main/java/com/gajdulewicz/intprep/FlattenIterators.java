package com.gajdulewicz.intprep;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Flatten an iterator of iterators, i.e. return all elements of subiterators contained in a parent
 * iterator Do this without materializing all elements.
 */
public class FlattenIterators implements Solution {
  @Override
  public void solve() {
    List<List<String>> input = new ArrayList<>();
    input.add(Lists.newArrayList("a", "b"));
    input.add(Lists.newArrayList());
    input.add(Lists.newArrayList());
    input.add(Lists.newArrayList("c"));
    input.add(Lists.newArrayList("d", "e", "f"));
    input.add(Lists.newArrayList());
    input.add(Lists.newArrayList());
    Iterator<String> flat =
        new Flattener<>(input.stream().map(List::iterator).iterator()).flatten();
    System.out.println(Arrays.toString(Iterators.toArray(flat, String.class)));
  }

  public static class Flattener<T> {
    private Iterator<Iterator<T>> iterators;

    public Flattener(Iterator<Iterator<T>> iterators) {
      this.iterators = iterators;
    }

    public Iterator<T> flatten() {
      return new Iterator<T>() {
        private Iterator<T> current;

        @Override
        public boolean hasNext() {
          if (current != null && current.hasNext()) {
            return true;
          }
          while (iterators.hasNext()) {
            current = iterators.next();
            if (current.hasNext()) {
              return true;
            }
          }
          return false;
        }

        @Override
        public T next() {
          return current.next();
        }
      };
    }
  }
}
