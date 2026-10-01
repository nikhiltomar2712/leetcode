class Solution {
    public boolean checkZeroOnes(String s) {
        int max1 = 0, max0 = 0, cur1 = 0, cur0 = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                cur1++;
                cur0 = 0;
                max1 = Math.max(max1, cur1);
            } else {
                cur0++;
                cur1 = 0;
                max0 = Math.max(max0, cur0);
            }
        }
        return max1 > max0;
    }
}