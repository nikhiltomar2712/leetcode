class Solution {
    public long countSubstrings(String s) {
        long ans = 0;
        // dp[num][rem] = number of substrings ending at the previous position
        //                that have remainder `rem` when divided by `num`
        int[][] dp = new int[10][10];

        for (char c : s.toCharArray()) {
            int digit = c - '0';
            int[][] newDp = new int[10][10];

            for (int num = 1; num <= 9; num++) {
                // the single-digit substring consisting of the current digit
                newDp[num][digit % num]++;

                // extend all previous substrings
                for (int rem = 0; rem < num; rem++) {
                    if (dp[num][rem] > 0) {
                        int newRem = (rem * 10 + digit) % num;
                        newDp[num][newRem] += dp[num][rem];
                    }
                }
            }

            // if the last digit is non-zero, add the count of valid substrings
            if (digit != 0) {
                ans += newDp[digit][0];
            }

            dp = newDp;
        }
        return ans;
    }
}