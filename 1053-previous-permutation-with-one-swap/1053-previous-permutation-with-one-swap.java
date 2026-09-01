class Solution {
    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        
        // Step 1: Find the largest index i such that arr[i] > arr[i+1]
        int i = n - 2;
        while (i >= 0 && arr[i] <= arr[i + 1]) {
            i--;
        }
        
        // Already the smallest permutation
        if (i < 0) {
            return arr;
        }
        
        // Step 2: Find the largest index j > i such that arr[j] < arr[i]
        // Prefer the leftmost occurrence of that value (skip duplicates from the right)
        int j = n - 1;
        while (arr[j] >= arr[i] || (j > 0 && arr[j] == arr[j - 1])) {
            j--;
        }
        
        // Step 3: Swap
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        
        return arr;
    }
}