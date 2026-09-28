class Solution {
    public int countHomogenous(String s) {
        int mod = 1000000007;
        long res = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                count = 1;
            }
            res = (res + count) % mod;
        }
        return (int) res;
    }
}