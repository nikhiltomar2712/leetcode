class Solution {
    public int countVowelStrings(int n) {
        // dp[j] = number of strings of current length ending with vowel j
        int[] dp = new int[5];
        Arrays.fill(dp, 1);  // length 1: each vowel has 1 string

        for (int i = 2; i <= n; i++) {
            for (int j = 3; j >= 0; j--) {
                dp[j] += dp[j + 1];  // ending with vowel j: sum over vowels >= j
            }
        }

        int total = 0;
        for (int v : dp) total += v;
        return total;
    }
}