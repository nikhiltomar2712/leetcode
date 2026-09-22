class Solution {
    public int numWays(String s) {
        final int MOD = 1_000_000_007;
        int n = s.length();

        // Count total ones
        int total = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') total++;
        }

        // Case 1: no ones at all
        if (total == 0) {
            long ways = (long)(n - 1) * (n - 2) / 2;
            return (int)(ways % MOD);
        }

        // Case 2: not divisible by 3
        if (total % 3 != 0) return 0;

        int onesPerPart = total / 3;

        // Find the position after the 1st group of ones
        int count = 0;
        int i = 0;
        for (; i < n; i++) {
            if (s.charAt(i) == '1') count++;
            if (count == onesPerPart) break;
        }
        int ways1 = 0;
        for (int j = i + 1; j < n && s.charAt(j) == '0'; j++) ways1++;

        // Find the position after the 2nd group of ones
        count = 0;
        int k = i + 1;
        for (; k < n; k++) {
            if (s.charAt(k) == '1') count++;
            if (count == onesPerPart) break;
        }
        int ways2 = 0;
        for (int j = k + 1; j < n && s.charAt(j) == '0'; j++) ways2++;

        long result = ((long)(ways1 + 1) * (ways2 + 1)) % MOD;
        return (int) result;
    }
}