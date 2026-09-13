class Solution {
    public int countOrders(int n) {
        long MOD = 1_000_000_007L;
        long result = 1;

        for (int i = 1; i <= n; i++) {
            // For the i-th order, there are (2i - 1) positions for pickup
            // and i positions for delivery among remaining slots
            result = (result * i * (2 * i - 1)) % MOD;
        }

        return (int) result;
    }
}