class Solution {
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;

        // suffix sums: rightEven[i], rightOdd[i] for indices i+1 .. n-1
        int[] rightEven = new int[n + 1];
        int[] rightOdd = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            rightEven[i] = rightEven[i + 1];
            rightOdd[i]  = rightOdd[i + 1];
            if (i % 2 == 0) {
                rightEven[i] += nums[i];
            } else {
                rightOdd[i] += nums[i];
            }
        }

        int count = 0;
        int leftEven = 0, leftOdd = 0;

        for (int i = 0; i < n; i++) {
            // After removing index i:
            // new even sum = leftEven + rightOdd[i+1]
            // new odd sum  = leftOdd  + rightEven[i+1]
            if (leftEven + rightOdd[i + 1] == leftOdd + rightEven[i + 1]) {
                count++;
            }

            // Add nums[i] into left accumulators before moving on
            if (i % 2 == 0) {
                leftEven += nums[i];
            } else {
                leftOdd += nums[i];
            }
        }

        return count;
    }
}