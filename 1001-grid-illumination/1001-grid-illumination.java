import java.util.*;

class Solution {
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        // Four maps to count lamps on rows / cols / diagonals
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        Map<Integer, Integer> diag1 = new HashMap<>();   // r - c
        Map<Integer, Integer> diag2 = new HashMap<>();   // r + c

        // Set of currently lit lamps (encoded as long)
        Set<Long> lit = new HashSet<>();

        // Turn on all given lamps (ignore duplicates)
        for (int[] lamp : lamps) {
            int r = lamp[0], c = lamp[1];
            long key = encode(r, c);
            if (lit.add(key)) {                 // only add once
                row.merge(r, 1, Integer::sum);
                col.merge(c, 1, Integer::sum);
                diag1.merge(r - c, 1, Integer::sum);
                diag2.merge(r + c, 1, Integer::sum);
            }
        }

        int[] ans = new int[queries.length];
        int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,0},{0,1},{1,-1},{1,0},{1,1}};

        for (int i = 0; i < queries.length; i++) {
            int r = queries[i][0];
            int c = queries[i][1];

            // Check if illuminated
            if (row.getOrDefault(r, 0) > 0 ||
                col.getOrDefault(c, 0) > 0 ||
                diag1.getOrDefault(r - c, 0) > 0 ||
                diag2.getOrDefault(r + c, 0) > 0) {
                ans[i] = 1;
            }

            // Turn off the 3×3 neighborhood
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

                long key = encode(nr, nc);
                if (lit.remove(key)) {          // only if it was lit
                    dec(row, nr);
                    dec(col, nc);
                    dec(diag1, nr - nc);
                    dec(diag2, nr + nc);
                }
            }
        }
        return ans;
    }

    private long encode(int r, int c) {
        return ((long) r << 32) | (c & 0xffffffffL);
    }

    private void dec(Map<Integer, Integer> map, int key) {
        int val = map.getOrDefault(key, 0) - 1;
        if (val == 0) map.remove(key);
        else map.put(key, val);
    }
}