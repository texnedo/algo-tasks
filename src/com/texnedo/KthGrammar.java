package com.texnedo;

public class KthGrammar {
    public static void main(String[] args) {
        KthGrammar grammar = new KthGrammar();
        System.out.println(grammar.kthGrammar(1, 1));
        System.out.println(grammar.kthGrammar(2, 1));
        System.out.println(grammar.kthGrammar(2, 2));
    }

    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        double rowLength = Math.pow(2, n - 1);
        int halfLength = (int) rowLength / 2;
        if (k <= halfLength) {
            return kthGrammar(n - 1, k);
        } else {
            int previous = kthGrammar(n - 1, k - halfLength);
            return previous == 1 ? 0 : 1;
        }
    }
}
