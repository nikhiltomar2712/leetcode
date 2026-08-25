class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int[] result = new int[k];
        
        // Try all possible numbers of digits to take from nums1
        for (int i = Math.max(0, k - n); i <= Math.min(k, m); i++) {
            // Take i digits from nums1, k-i digits from nums2
            int[] sub1 = maxSubsequence(nums1, i);
            int[] sub2 = maxSubsequence(nums2, k - i);
            
            // Merge the two subsequences
            int[] merged = merge(sub1, sub2);
            
            // Update result if this merge is lexicographically larger
            if (greater(merged, 0, result, 0)) {
                result = merged;
            }
        }
        
        return result;
    }
    
    // Get the maximum subsequence of length k from nums
    private int[] maxSubsequence(int[] nums, int k) {
        if (k == 0) return new int[0];
        if (k == nums.length) return nums.clone();
        
        int[] stack = new int[k];
        int top = -1;
        int toRemove = nums.length - k;
        
        for (int num : nums) {
            // Remove smaller elements if we can still achieve length k
            while (top >= 0 && toRemove > 0 && stack[top] < num) {
                top--;
                toRemove--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                toRemove--;
            }
        }
        
        return stack;
    }
    
    // Merge two arrays to form the maximum number
    private int[] merge(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] merged = new int[m + n];
        int i = 0, j = 0, idx = 0;
        
        while (i < m && j < n) {
            if (greater(nums1, i, nums2, j)) {
                merged[idx++] = nums1[i++];
            } else {
                merged[idx++] = nums2[j++];
            }
        }
        
        while (i < m) merged[idx++] = nums1[i++];
        while (j < n) merged[idx++] = nums2[j++];
        
        return merged;
    }
    
    // Check if nums1 starting at i is lexicographically greater than nums2 starting at j
    private boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] != nums2[j]) {
                return nums1[i] > nums2[j];
            }
            i++;
            j++;
        }
        return (nums1.length - i) > (nums2.length - j);
    }
    
    // Check if nums1 is lexicographically greater than nums2
    private boolean greater(int[] nums1, int[] nums2) {
        return greater(nums1, 0, nums2, 0);
    }
}