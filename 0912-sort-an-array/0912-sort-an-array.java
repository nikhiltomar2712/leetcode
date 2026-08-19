class Solution {
    public int[] sortArray(int[] nums) {
        // Use Merge Sort for guaranteed O(n log n) time and O(n) space
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        // Create temporary arrays for the two halves
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArr[i] = nums[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = nums[mid + 1 + j];
        }

        // Merge the two arrays back into nums
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                nums[k] = leftArr[i];
                i++;
            } else {
                nums[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArr, if any
        while (i < n1) {
            nums[k] = leftArr[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArr, if any
        while (j < n2) {
            nums[k] = rightArr[j];
            j++;
            k++;
        }
    }
}