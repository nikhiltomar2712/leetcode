class Solution {
    public int findIntegers(int n) {
        String s = Integer.toBinaryString(n);
        int m = s.length();
        Integer[][] memo = new Integer[m + 1][2];
        return dp(0, 0, true, s, memo);
    }
    
    private int dp(int pos, int prev, boolean tight, String s, Integer[][] memo) {
        if (pos == s.length()) return 1;
        if (memo[pos][prev] != null && !tight) return memo[pos][prev];
        
        int ans = 0;
        int up = tight ? s.charAt(pos) - '0' : 1;
        
        for (int d = 0; d <= up; d++) {
            if (prev == 1 && d == 1) continue;
            ans += dp(pos + 1, d, tight && (d == up), s, memo);
        }
        
        if (!tight) memo[pos][prev] = ans;
        return ans;
    }
}