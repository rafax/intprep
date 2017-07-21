package com.gajdulewicz.intprep;

import java.util.*;

public class ContactTrie {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Trie t = new Trie();
        for (int a0 = 0; a0 < n; a0++) {
            String op = in.next();
            String contact = in.next();
            if (Objects.equals(op, "add")) {
                t.put(contact);
            }
            if (Objects.equals(op, "find")) {
                System.out.println(t.countStartingWith(contact));
            }
        }
    }

    public static class Trie implements PrefixCounter {

        private final Map<Character, Node> children = new HashMap<>();

        Trie() {
            for (char l = 'a'; l <= 'z'; l++) {
                children.put(l, new Node(l));
            }
        }

        public void put(String word) {
            children.get(word.charAt(0)).put(word);
        }

        @Override
        public int countStartingWith(String s) {
            final char[] chars = s.toCharArray();
            Node n = children.get(s.charAt(0));
            for (int i = 1; i < chars.length; i++) {
                if (!n.children.containsKey(chars[i])) {
                    return 0;
                }
                n = n.children.get(chars[i]);
            }
            return n.getSuccessorCount();
        }

        static class Node {
            public final char letter;
            public final Map<Character, Node> children;
            private int successorCount = 0;

            Node(char letter) {
                this.letter = letter;
                this.children = new HashMap<>();
            }

            public void put(String word) {
                if (word == null || word.isEmpty()) return;
                char head = word.charAt(0);
                if (this.letter != head) {
                    throw new RuntimeException("Letters do not match: " + this.letter + "!=" + head);
                }
                successorCount++;
                if (word.length() > 1) {
                    Node child = children.get(word.charAt(1));
                    if (!this.children.containsKey(word.charAt(1))) {
                        child = new Node(word.charAt(1));
                        this.children.put(word.charAt(1), child);
                    }
                    child.put(word.substring(1));
                }
            }

            public int getSuccessorCount() {
                return successorCount;
            }
        }
    }

    public static class PrefixMap implements PrefixCounter {

        private Map<String, Integer> prefixCount = new HashMap<>();

        @Override
        public void put(String in) {
            final char[] chars = in.toCharArray();
            for (int i = chars.length; i >= 1; i--) {
                String prefix = in.substring(0, i);
                if (!prefixCount.containsKey(prefix)) {
                    prefixCount.put(prefix, 0);
                }
                prefixCount.put(prefix, prefixCount.get(prefix) + 1);
            }
        }

        @Override
        public int countStartingWith(String s) {
            return prefixCount.getOrDefault(s, 0);
        }
    }

    public interface PrefixCounter {
        void put(String in);

        int countStartingWith(String s);
    }
}
