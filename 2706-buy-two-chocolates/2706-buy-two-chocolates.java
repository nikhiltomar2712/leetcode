class Solution {
    public int buyChoco(int[] prices, int money) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int p : prices) {
            if (p < min1) {
                min2 = min1;
                min1 = p;
            } else if (p < min2) {
                min2 = p;
            }
        }
        int cost = min1 + min2;
        return cost <= money ? money - cost : money;
    }
}