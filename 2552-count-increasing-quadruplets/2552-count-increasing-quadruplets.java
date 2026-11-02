class Solution {
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        long ans = 0;
        
        // smallerCounts[v] will store the number of elements with value < v 
        // in the index range [0, j-1] (to the left of current j)
        int[] smallerCounts = new int[n + 1];

        for (int j = 0; j < n; j++) {
            // rightCount tracks the number of elements > nums[j] 
            // in the range (k, n-1] as we move k from right to left
            int rightCount = 0;

            // Iterate k backwards from the end towards j+1
            for (int k = n - 1; k > j; k--) {
                if (nums[j] > nums[k]) {
                    // Valid middle pair found (nums[j] > nums[k])
                    // smallerCounts[nums[k]] = number of valid i's
                    // rightCount = number of valid l's
                    ans += (long) smallerCounts[nums[k]] * rightCount;
                } else {
                    // nums[k] > nums[j], so this nums[k] can serve as a valid 'l'
                    // for future (smaller) k values
                    rightCount++;
                }
            }

            // After finishing the inner loop for this j, 
            // update smallerCounts for the next j (j+1)
            // nums[j] will now be on the "left" side for subsequent iterations
            for (int val = nums[j] + 1; val <= n; val++) {
                smallerCounts[val]++;
            }
        }
        
        return ans;
    }
}