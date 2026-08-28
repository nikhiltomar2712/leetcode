class Solution {
    private char[] s;
    private Integer[][] memo;   // memo[i][mask]

    public int numDupDigitsAtMostN(int n) {
        s = String.valueOf(n).toCharArray();
        memo = new Integer[s.length][1 << 10];
        // numbers with unique digits
        int unique = dfs(0, 0, true, true);
        return n - unique;
    }

    private int dfs(int i, int mask, boolean lead, boolean limit) {
        if (i == s.length) {
            return lead ? 0 : 1;   // leading zeros means the number is 0 → not counted
        }

        if (!lead && !limit && memo[i][mask] != null) {
            return memo[i][mask];
        }

        int up = limit ? s[i] - '0' : 9;
        int ans = 0;

        for (int d = 0; d <= up; d++) {
            if (lead && d == 0) {
                // still leading zeros
                ans += dfs(i + 1, mask, true, false);
            } else if ((mask & (1 << d)) == 0) {
                // digit not used yet
                ans += dfs(i + 1, mask | (1 << d), false, limit && d == up);
            }
        }

        if (!lead && !limit) {
            memo[i][mask] = ans;
        }
        return ans;
    }
}