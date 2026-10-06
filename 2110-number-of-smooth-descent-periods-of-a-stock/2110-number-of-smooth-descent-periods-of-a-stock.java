class Solution {
    public long getDescentPeriods(int[] prices) {
        long res = 1;
        long len = 1;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                len++;
            } else {
                len = 1;
            }
            res += len;
        }
        return res;
    }
}