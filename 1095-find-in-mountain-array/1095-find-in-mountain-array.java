/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */

class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();
        
        // 1. Find the peak index
        int peak = findPeak(mountainArr, n);
        
        // 2. Search in the ascending part (left of peak)
        int leftResult = binarySearchAscending(mountainArr, 0, peak, target);
        if (leftResult != -1) {
            return leftResult;
        }
        
        // 3. Search in the descending part (right of peak)
        return binarySearchDescending(mountainArr, peak + 1, n - 1, target);
    }
    
    // Find peak index
    private int findPeak(MountainArray arr, int n) {
        int lo = 0, hi = n - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr.get(mid) < arr.get(mid + 1)) {
                lo = mid + 1;          // peak is on the right
            } else {
                hi = mid;              // peak is on the left (or mid)
            }
        }
        return lo;
    }
    
    // Binary search on strictly increasing side
    private int binarySearchAscending(MountainArray arr, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int val = arr.get(mid);
            if (val == target) {
                return mid;
            } else if (val < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
    
    // Binary search on strictly decreasing side
    private int binarySearchDescending(MountainArray arr, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int val = arr.get(mid);
            if (val == target) {
                return mid;
            } else if (val > target) {   // note the reversed comparison
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}