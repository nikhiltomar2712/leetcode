class Solution {
    private static final int[] POW_10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000};
    private boolean[] used = new boolean[10];
    private int[] charDigit = new int[26];
    private boolean[] leading = new boolean[26];
    private int[] weights = new int[26]; // net coefficient of each char
    private List<Character> chars = new ArrayList<>();
    private int charCount;

    public boolean isSolvable(String[] words, String result) {
        // Compute net coefficient (weight) for each character
        // words contribute +, result contributes -
        for (String w : words) {
            int len = w.length();
            for (int i = 0; i < len; i++) {
                char c = w.charAt(i);
                weights[c - 'A'] += POW_10[len - 1 - i];
                if (i == 0 && len > 1) leading[c - 'A'] = true;
            }
        }
        int rlen = result.length();
        for (int i = 0; i < rlen; i++) {
            char c = result.charAt(i);
            weights[c - 'A'] -= POW_10[rlen - 1 - i];
            if (i == 0 && rlen > 1) leading[c - 'A'] = true;
        }

        // Collect only characters that actually appear
        for (int i = 0; i < 26; i++) {
            if (weights[i] != 0 || leading[i]) {
                chars.add((char) ('A' + i));
            }
        }
        charCount = chars.size();
        if (charCount > 10) return false;

        // Initialize charDigit to -1
        Arrays.fill(charDigit, -1);

        return dfs(0);
    }

    private boolean dfs(int idx) {
        if (idx == charCount) {
            // All characters assigned; check if weighted sum is zero
            long sum = 0;
            for (char c : chars) {
                sum += (long) weights[c - 'A'] * charDigit[c - 'A'];
            }
            return sum == 0;
        }

        char c = chars.get(idx);
        int ci = c - 'A';
        for (int d = 0; d <= 9; d++) {
            if (used[d]) continue;
            if (d == 0 && leading[ci]) continue; // no leading zero

            used[d] = true;
            charDigit[ci] = d;

            // Prune: partial assignment must be able to reach zero
            if (canPrune(idx)) {
                used[d] = false;
                charDigit[ci] = -1;
                continue;
            }

            if (dfs(idx + 1)) return true;

            used[d] = false;
            charDigit[ci] = -1;
        }
        return false;
    }

    // Simple pruning: check current partial weighted sum against remaining max
    private boolean canPrune(int idx) {
        long sum = 0;
        long remaining = 0;
        for (int i = 0; i <= idx; i++) {
            int ci = chars.get(i) - 'A';
            if (charDigit[ci] != -1) {
                sum += (long) weights[ci] * charDigit[ci];
            }
        }
        for (int i = idx + 1; i < charCount; i++) {
            int ci = chars.get(i) - 'A';
            remaining += Math.abs((long) weights[ci]) * 9;
        }
        return Math.abs(sum) > remaining;
    }
}