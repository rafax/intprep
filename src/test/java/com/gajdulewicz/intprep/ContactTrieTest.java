package com.gajdulewicz.intprep;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by gajduler on 7/20/17.
 */
public class ContactTrieTest {

    Supplier<List<ContactTrie.PrefixCounter>> implementations = () -> Lists.newArrayList(new ContactTrie.PrefixMap(), new ContactTrie.Trie());

    @Test
    public void testExample() {
        implementations.get().forEach(t -> {
            t.put("hack");
            t.put("hackerrank");
            assertThat(t.countStartingWith("hac")).isEqualTo(2);
            assertThat(t.countStartingWith("hak")).isEqualTo(0);
        });

    }

    @Test
    public void testCase1() {
        implementations.get().forEach(t -> {
            t.put("s");
            t.put("ss");
            t.put("sss");
            t.put("ssss");
            t.put("sssss");
            assertThat(t.countStartingWith("s")).isEqualTo(5);
            assertThat(t.countStartingWith("ss")).isEqualTo(4);
            assertThat(t.countStartingWith("sss")).isEqualTo(3);
            assertThat(t.countStartingWith("ssss")).isEqualTo(2);
            assertThat(t.countStartingWith("sssss")).isEqualTo(1);
            assertThat(t.countStartingWith("ssssss")).isEqualTo(0);
        });

    }

    @Test
    public void testTriePut() {
        ContactTrie.Trie t = new ContactTrie.Trie();
        t.put("s");
        t.put("ss");
        t.put("sss");
        t.put("ssss");
        t.put("sssss");
        assertThat(t.countStartingWith("s")).isEqualTo(5);
        assertThat(t.countStartingWith("ss")).isEqualTo(4);
        assertThat(t.countStartingWith("sss")).isEqualTo(3);
        assertThat(t.countStartingWith("ssss")).isEqualTo(2);
        assertThat(t.countStartingWith("sssss")).isEqualTo(1);
        assertThat(t.countStartingWith("ssssss")).isEqualTo(0);

    }
}
