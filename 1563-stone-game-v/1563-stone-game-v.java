class Solution {
    private int[] prefix;
    private Integer[][] memo;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }
        memo = new Integer[n][n];
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i >= j) return 0;               // only one stone left
        if (memo[i][j] != null) return memo[i][j];

        int ans = 0;
        int total = prefix[j + 1] - prefix[i];
        int leftSum = 0;

        for (int k = i; k < j; k++) {
            leftSum += (prefix[k + 1] - prefix[k]); // or just stoneValue[k]
            int rightSum = total - leftSum;

            if (leftSum < rightSum) {
                // Bob throws away right, Alice keeps left
                ans = Math.max(ans, leftSum + dfs(i, k));
            } else if (leftSum > rightSum) {
                // Bob throws away left, Alice keeps right
                ans = Math.max(ans, rightSum + dfs(k + 1, j));
            } else {
                // Alice chooses the better option
                ans = Math.max(ans, leftSum + Math.max(dfs(i, k), dfs(k + 1, j)));
            }
        }

        return memo[i][j] = ans;
    }
}