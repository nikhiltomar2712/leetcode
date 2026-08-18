class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // Case 1: k == n → only one subarray (the whole array)
        if (k == n) {
            int max = nums[0];
            for (int x : nums) max = Math.max(max, x);
            return max;
        }
        
        // Frequency of every value
        int[] freq = new int[51];
        for (int x : nums) freq[x]++;
        
        // Case 2: k == 1 → each element is its own subarray
        if (k == 1) {
            int ans = -1;
            for (int x = 0; x <= 50; x++) {
                if (freq[x] == 1) ans = Math.max(ans, x);
            }
            return ans;
        }
        
        // Case 3: 1 < k < n
        // Only the two endpoints can possibly appear in exactly one window
        int a = nums[0];
        int b = nums[n - 1];
        
        int ans = -1;
        if (freq[a] == 1) ans = a;
        if (freq[b] == 1) ans = Math.max(ans, b);
        
        return ans;
    }
}