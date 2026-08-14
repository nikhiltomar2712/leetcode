class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // If mid is on the increasing slope, peak is to the right
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } 
            // If mid is on the decreasing slope, peak is to the left (or at mid)
            else {
                right = mid;
            }
        }
        
        return left; // or right, both point to the peak
    }
}