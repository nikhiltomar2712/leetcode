class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIdx = 0, maxIdx = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) minIdx = i;
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }

        int a = Math.min(minIdx, maxIdx);
        int b = Math.max(minIdx, maxIdx);

        // Three possible strategies
        int fromFront = b + 1;                    // remove both from front
        int fromBack  = n - a;                    // remove both from back
        int fromBoth  = a + 1 + (n - b);          // one from front, one from back

        return Math.min(fromFront, Math.min(fromBack, fromBoth));
    }
}