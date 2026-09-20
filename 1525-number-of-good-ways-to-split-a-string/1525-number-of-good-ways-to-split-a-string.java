class Solution {
    public int numSplits(String s) {
        int n = s.length();
        int[] left = new int[n];
        int[] right = new int[n];

        boolean[] seen = new boolean[26];
        int distinct = 0;
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (!seen[c]) { seen[c] = true; distinct++; }
            left[i] = distinct;
        }

        seen = new boolean[26];
        distinct = 0;
        for (int i = n - 1; i >= 0; i--) {
            int c = s.charAt(i) - 'a';
            if (!seen[c]) { seen[c] = true; distinct++; }
            right[i] = distinct;
        }

        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            if (left[i] == right[i + 1]) count++;
        }

        return count;
    }
}