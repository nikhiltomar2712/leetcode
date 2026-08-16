class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        
        // Pair (value, original index) of nums2
        int[][] t = new int[n][2];
        for (int i = 0; i < n; i++) {
            t[i][0] = nums2[i];
            t[i][1] = i;
        }
        
        // Sort nums2 pairs by value ascending
        Arrays.sort(t, (a, b) -> a[0] - b[0]);
        // Sort nums1 ascending
        Arrays.sort(nums1);
        
        int[] ans = new int[n];
        int left = 0;          // pointer to smallest remaining in nums2
        int right = n - 1;     // pointer to largest remaining in nums2
        
        for (int v : nums1) {
            if (v > t[left][0]) {
                // Can beat the current smallest → assign to it
                ans[t[left][1]] = v;
                left++;
            } else {
                // Cannot beat → assign to the largest (waste it)
                ans[t[right][1]] = v;
                right--;
            }
        }
        
        return ans;
    }
}