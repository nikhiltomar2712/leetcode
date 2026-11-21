class Solution {
    public int maxOperations(String s) {
        int res = 0;
        int ones = 0;
        int i = 0;
        int n = s.length();
        while (i < n) {
            if (s.charAt(i) == '1') {
                ones++;
                i++;
            } else {
                while (i < n && s.charAt(i) == '0') i++;
                res += ones;
            }
        }
        return res;
    }
}