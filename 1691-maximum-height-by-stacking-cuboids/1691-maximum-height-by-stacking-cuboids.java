import java.util.Arrays;

class Solution {
    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;
        
        // Step 1: Sort dimensions of each cuboid (smallest to largest)
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }
        
        // Step 2: Sort all cuboids in ascending order
        Arrays.sort(cuboids, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });
        
        // Step 3: DP - dp[i] = max height of stack ending with cuboid i
        int[] dp = new int[n];
        int ans = 0;
        
        for (int i = 0; i < n; i++) {
            dp[i] = cuboids[i][2]; // Base case: cuboid i alone
            for (int j = 0; j < i; j++) {
                // Check if cuboid i can be placed on cuboid j
                if (cuboids[i][0] >= cuboids[j][0] &&
                    cuboids[i][1] >= cuboids[j][1] &&
                    cuboids[i][2] >= cuboids[j][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        
        return ans;
    }
}