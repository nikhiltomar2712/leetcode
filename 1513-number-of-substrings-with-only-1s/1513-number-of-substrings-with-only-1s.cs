public class Solution {
    public int NumSub(string s) {
        const int MOD = 1_000_000_007;
        long ans = 0;
        int cur = 0;

        foreach (char c in s) {
            if (c == '1') {
                cur++;
            } else {
                ans = (ans + (long)cur * (cur + 1) / 2) % MOD;
                cur = 0;
            }
        }
        // handle the last group if the string ends with 1s
        ans = (ans + (long)cur * (cur + 1) / 2) % MOD;

        return (int)ans;
    }
}