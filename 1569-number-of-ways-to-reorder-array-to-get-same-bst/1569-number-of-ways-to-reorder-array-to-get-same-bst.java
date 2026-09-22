class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Precompute Pascal's triangle for binomial coefficients
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int x : nums) list.add(x);

        // Subtract 1 to exclude the original arrangement
        return (int) ((countWays(list) - 1 + MOD) % MOD);
    }

    private long countWays(List<Integer> nums) {
        if (nums.size() <= 1) return 1;

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) left.add(nums.get(i));
            else right.add(nums.get(i));
        }

        long ways = comb[left.size() + right.size()][left.size()];
        ways = ways * countWays(left) % MOD;
        ways = ways * countWays(right) % MOD;
        return ways;
    }
}