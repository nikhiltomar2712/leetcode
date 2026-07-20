class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int median = findKthLargest(nums, (n + 1) / 2);
        
        // Virtual index: A(i) = (1 + 2 * i) % (n | 1)
        int i = 0, j = 0, k = n - 1;
        while (i <= k) {
            if (nums[A(i, n)] > median) {
                swap(nums, A(i++, n), A(j++, n));
            } else if (nums[A(i, n)] < median) {
                swap(nums, A(i, n), A(k--, n));
            } else {
                i++;
            }
        }
    }
    
    private int A(int i, int n) {
        return (1 + 2 * i) % (n | 1);
    }
    
    private int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }
    
    private int quickSelect(int[] nums, int l, int r, int k) {
        int pivot = nums[r];
        int nextSwapped = l;
        for (int i = l; i < r; i++) {
            if (nums[i] >= pivot) {
                swap(nums, nextSwapped++, i);
            }
        }
        swap(nums, nextSwapped, r);
        
        int count = nextSwapped - l + 1; // numbers >= pivot
        if (count == k) {
            return nums[nextSwapped];
        } else if (count > k) {
            return quickSelect(nums, l, nextSwapped - 1, k);
        } else {
            return quickSelect(nums, nextSwapped + 1, r, k - count);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}