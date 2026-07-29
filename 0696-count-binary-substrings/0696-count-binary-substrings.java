class Solution {
    public int countBinarySubstrings(String s) {
        int ans = 0;
        int prev = 0;   // length of previous group
        int cur = 1;    // length of current group

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cur++;
            } else {
                ans += Math.min(prev, cur);
                prev = cur;
                cur = 1;
            }
        }
        // don't forget the last pair of groups
        ans += Math.min(prev, cur);
        return ans;
    }
}