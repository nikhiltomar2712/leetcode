class Solution {
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int left = 1, right = n;
        
        // First k elements: alternate low and high to create k distinct differences
        for (int i = 0; i < k; i++) {
            if (i % 2 == 0) {
                ans[i] = left++;
            } else {
                ans[i] = right--;
            }
        }
        
        // Remaining elements: continue in sorted order (difference = 1)
        for (int i = k; i < n; i++) {
            if (k % 2 == 0) {
                ans[i] = right--;
            } else {
                ans[i] = left++;
            }
        }
        
        return ans;
    }
}