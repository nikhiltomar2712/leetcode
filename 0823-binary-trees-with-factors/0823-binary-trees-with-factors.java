class Solution {
    public int numFactoredBinaryTrees(int[] arr) {
        int mod = 1000000007;
        Arrays.sort(arr);
        int n = arr.length;
        long[] dp = new long[n];
        Arrays.fill(dp, 1);
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(arr[i], i);
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0) {
                    int other = arr[i] / arr[j];
                    if (index.containsKey(other)) {
                        dp[i] = (dp[i] + dp[j] * dp[index.get(other)]) % mod;
                    }
                }
            }
            res = (res + dp[i]) % mod;
        }
        return (int) res;
    }
}