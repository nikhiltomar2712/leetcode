class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int target = threshold * k; // avoid division
        int n = arr.length;
        int count = 0;
        
        // Compute sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        if (windowSum >= target) count++;
        
        // Slide the window
        for (int i = k; i < n; i++) {
            windowSum += arr[i] - arr[i - k];
            if (windowSum >= target) count++;
        }
        
        return count;
    }
}