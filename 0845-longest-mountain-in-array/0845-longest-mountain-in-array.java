class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        if (n < 3) return 0;
        
        int[] left = new int[n];  // Length of increasing sequence ending at i
        int[] right = new int[n]; // Length of decreasing sequence starting at i
        
        // Fill left array (increasing sequences)
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        
        // Fill right array (decreasing sequences)
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        
        // Find max mountain
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            if (left[i] > 0 && right[i] > 0) {
                maxLength = Math.max(maxLength, left[i] + right[i] + 1);
            }
        }
        
        return maxLength;
    }
}