class Solution {
    public int findBestValue(int[] arr, int target) {
        int left = 0;
        int right = 0;
        for (int num : arr) {
            right = Math.max(right, num);
        }
        
        // Binary search for the best value
        while (left < right) {
            int mid = left + (right - left) / 2;
            long sum1 = getSum(arr, mid);
            long sum2 = getSum(arr, mid + 1);
            
            // We prefer the smaller value on a tie
            if (Math.abs(sum1 - target) <= Math.abs(sum2 - target)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private long getSum(int[] arr, int value) {
        long sum = 0;
        for (int num : arr) {
            sum += Math.min(num, value);
        }
        return sum;
    }
}