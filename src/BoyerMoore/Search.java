package BoyerMoore;

import java.util.LinkedList;
import java.util.List;

public class Search {
    final int ALPHABET_SIZE = 256;

    public List<Integer> search(char[] T, char[] P) {
        int n = T.length;
        int m = P.length;
        int i = m - 1;
        int j = i;
        int[] last = initLast(P);
        List<Integer> indices = new LinkedList<>();

        do {
            if (P[j] == T[i]) {
                if (j == 0) {
                    indices.add(i);
                    i = i + m - Math.min(j, 1 + last[T[i]]);
                    j = m -1;
                } else {
                    i--;
                    j--;
                }
            } else {
                i = i + m - Math.min(j, 1 + last[T[i]]);
                j = m-1;
            }
        } while (i < n-1);

        return indices;
    }

    private int[] initLast(char[] P) {
        int[] last = new int[ALPHABET_SIZE];

        for (int i = 0; i < last.length; i++) {
            last[i] = -1;
        }

        for (int i = 0; i < P.length; i++) {
            last[P[i]] = i;
        }

        return last;
    }
}
