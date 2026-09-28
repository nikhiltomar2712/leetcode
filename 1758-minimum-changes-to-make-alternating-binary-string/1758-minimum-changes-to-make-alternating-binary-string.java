class Solution {
    public int minOperations(String s) {
        int n = s.length();
        int start0 = 0, start1 = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                if (s.charAt(i) == '1') start0++;
                else start1++;
            } else {
                if (s.charAt(i) == '0') start0++;
                else start1++;
            }
        }
        return Math.min(start0, start1);
    }
}