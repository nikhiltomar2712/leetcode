class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        
        // max ending here without any deletion
        int noDelete = arr[0];
        // max ending here with one deletion already done
        int oneDelete = 0;
        
        int maxSum = arr[0];
        
        for (int i = 1; i < n; i++) {
            // Option 1: delete current element → take previous noDelete
            // Option 2: keep current element and extend a previous oneDelete
            oneDelete = Math.max(noDelete, oneDelete + arr[i]);
            
            // Standard Kadane for no deletion
            noDelete = Math.max(arr[i], noDelete + arr[i]);
            
            maxSum = Math.max(maxSum, Math.max(noDelete, oneDelete));
        }
        
        return maxSum;
    }
}