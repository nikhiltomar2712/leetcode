class Solution {
    public int pivotInteger(int n) {
        int total = n * (n + 1) / 2;
        int sum = 0;
        for (int x = 1; x <= n; x++) {
            sum += x;
            if (sum == total - sum + x) {
                return x;
            }
        }
        return -1;
    }
}