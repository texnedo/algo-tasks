package com.texnedo.architecture;

import java.util.HashSet;

class WordDictionary {
    private HashSet<String> wordSet = new HashSet<>();
    private TrieNode trie = new TrieNode(null);

    private class TrieNode {
        private final Character value;
        private TrieNode[] next;

        TrieNode(Character value) {
            this.value = value;
        }

        TrieNode addNextValue(char nextValue) {
            if (next == null) {
                next = new TrieNode[28];
            }
            int offset = nextValue - 'a';
            next[offset] = new TrieNode(nextValue);
            return next[offset];
        }

        void addWord(String word) {
            TrieNode current = this;
            for (int i = 0; i < word.length(); i++) {
                current = current.addNextValue(word.charAt(i));
            }
        }

        boolean contains(String expr, int start) {
            if (expr == null || expr.length() == 0) {
                return false;
            }
            if (start > expr.length() - 1) {
                throw new IllegalArgumentException();
            }
            TrieNode current = this;
            for (int i = start; i < expr.length(); i++) {
                char value = expr.charAt(i);
                if (value == '.') {
                    for (int offset = 0; offset < current.next.length; offset++) {
                        if (current.next[offset] != null) {
                            if (i == expr.length() - 1) {
                                return true;
                            }
                            if (current.next[offset].contains(expr, i + 1)) {
                                return true;
                            }
                        }
                    }
                } else {
                    int offset = value - 'a';
                    if (current.next[offset] == null) {
                        return false;
                    }
                    current = current.next[offset];
                }
            }
            return true;
        }
    }

    /** Initialize your data structure here. */
    public WordDictionary() {

    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word == null || word.length() == 0) {
            throw new IllegalArgumentException();
        }
        if (wordSet.contains(word)) {
            return;
        }
        wordSet.add(word);
        trie.addWord(word);
    }

    /** Returns if the word is in the data structure.
     * A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (wordSet.contains(word)) {
            return true;
        }
        return trie.contains(word, 0);
    }

    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        dict.addWord("txac");
        //System.out.println(dict.search("pad"));
        //System.out.println(dict.search("bad"));
        System.out.println(dict.search(".ad"));
        //System.out.println(dict.search("b.."));
        System.out.println(dict.search(".x.c"));
        System.out.println(dict.search(".a."));
        System.out.println(dict.search(null));
    }
}
