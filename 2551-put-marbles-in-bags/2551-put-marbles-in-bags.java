class Solution {
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        if (n == 1 || k == 1) return 0;
        int[] splits = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            splits[i] = weights[i] + weights[i + 1];
        }
        Arrays.sort(splits);
        long minSum = 0, maxSum = 0;
        for (int i = 0; i < k - 1; i++) {
            minSum += splits[i];
            maxSum += splits[n - 2 - i];
        }
        return maxSum - minSum;
    }
}