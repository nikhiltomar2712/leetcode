class Solution {
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // keep = min swaps ending with NO swap at i
        // swap = min swaps ending with a swap at i
        int keep = 0;
        int swap = 1;

        for (int i = 1; i < n; i++) {
            int prevKeep = keep;
            int prevSwap = swap;
            keep = Integer.MAX_VALUE;
            swap = Integer.MAX_VALUE;

            // Case 1: no need to change relative order
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                keep = prevKeep;          // keep previous decision
                swap = prevSwap + 1;      // swap both positions
            }

            // Case 2: we can (or must) flip relative order
            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                keep = Math.min(keep, prevSwap);      // previous was swapped
                swap = Math.min(swap, prevKeep + 1);  // previous was kept
            }
        }
        return Math.min(keep, swap);
    }
}