class Solution {
    public int numOfWays(int[] nums) {
        int mod = 1000000007;
        int n = nums.length;
        long[][] comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % mod;
            }
        }
        return (int) (ways(nums, comb, mod) - 1 + mod) % mod;
    }

    private long ways(int[] nums, long[][] comb, int mod) {
        int n = nums.length;
        if (n <= 1) return 1;
        int root = nums[0];
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (nums[i] < root) left.add(nums[i]);
            else right.add(nums[i]);
        }
        long leftWays = ways(toArray(left), comb, mod);
        long rightWays = ways(toArray(right), comb, mod);
        long result = comb[n - 1][left.size()] * leftWays % mod * rightWays % mod;
        return result;
    }

    private int[] toArray(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) arr[i] = list.get(i);
        return arr;
    }
}