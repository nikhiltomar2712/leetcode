class Solution {
    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        
        // Check if already a zero array
        boolean alreadyZero = true;
        for (int num : nums) {
            if (num != 0) {
                alreadyZero = false;
                break;
            }
        }
        if (alreadyZero) return 0;

        // reachable[i][s] = true means we can form sum s for index i
        boolean[][] reachable = new boolean[n][1001];
        for (int i = 0; i < n; i++) {
            reachable[i][0] = true;   // empty subset
        }

        for (int k = 0; k < queries.length; k++) {
            int l = queries[k][0];
            int r = queries[k][1];
            int val = queries[k][2];

            // Update reachable sums for indices covered by this query
            for (int i = l; i <= r; i++) {
                // Traverse from high to low to avoid using newly added values in the same iteration
                for (int s = 1000 - val; s >= 0; s--) {
                    if (reachable[i][s]) {
                        reachable[i][s + val] = true;
                    }
                }
            }

            // Check if every position can form exactly nums[i]
            boolean allGood = true;
            for (int i = 0; i < n; i++) {
                if (!reachable[i][nums[i]]) {
                    allGood = false;
                    break;
                }
            }
            if (allGood) {
                return k + 1;
            }
        }

        return -1;
    }
}