class Solution {
    private String s;
    private int k;
    private Map<Long, Integer> memo = new HashMap<>();

    public int maxPartitionsAfterOperations(String s, int k) {
        this.s = s;
        this.k = k;
        return dfs(0, 0, true) + 1;
    }

    private int dfs(int i, int mask, boolean canChange) {
        if (i == s.length()) {
            return 0;
        }

        long key = ((long) i << 33) | ((long) mask << 1) | (canChange ? 1 : 0);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int bit = 1 << (s.charAt(i) - 'a');
        int newMask = mask | bit;
        int best;

        if (Integer.bitCount(newMask) > k) {
            best = 1 + dfs(i + 1, bit, canChange);
        } else {
            best = dfs(i + 1, newMask, canChange);
        }

        if (canChange) {
            for (int j = 0; j < 26; j++) {
                int changedMask = mask | (1 << j);
                if (Integer.bitCount(changedMask) > k) {
                    best = Math.max(best, 1 + dfs(i + 1, 1 << j, false));
                } else {
                    best = Math.max(best, dfs(i + 1, changedMask, false));
                }
            }
        }

        memo.put(key, best);
        return best;
    }
}