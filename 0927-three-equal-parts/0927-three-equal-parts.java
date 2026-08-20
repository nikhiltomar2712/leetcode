class Solution {
    public int[] threeEqualParts(int[] arr) {
        int n = arr.length;
        int totalOnes = 0;
        for (int num : arr) {
            totalOnes += num;
        }
        
        // Impossible if not divisible by 3
        if (totalOnes % 3 != 0) {
            return new int[]{-1, -1};
        }
        
        // Special case: all zeros
        if (totalOnes == 0) {
            return new int[]{0, n - 1};
        }
        
        int onesPerPart = totalOnes / 3;
        
        // Find the starting index of each group of ones
        int first = find(arr, 1);                    // start of 1st group
        int second = find(arr, onesPerPart + 1);     // start of 2nd group
        int third = find(arr, 2 * onesPerPart + 1);  // start of 3rd group
        
        // Compare the three parts bit by bit
        while (third < n && arr[first] == arr[second] && arr[second] == arr[third]) {
            first++;
            second++;
            third++;
        }
        
        // If we reached the end successfully
        if (third == n) {
            return new int[]{first - 1, second};
        }
        
        return new int[]{-1, -1};
    }
    
    // Find the index of the k-th '1'
    private int find(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                k--;
                if (k == 0) return i;
            }
        }
        return -1;
    }
}