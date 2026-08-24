class Solution {
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        int n = arr.length;

        // Place numbers from largest to smallest into their correct positions
        for (int size = n; size > 1; size--) {
            // Find the index of the current largest number (size)
            int idx = 0;
            while (arr[idx] != size) {
                idx++;
            }

            // Already in correct position
            if (idx == size - 1) continue;

            // Bring the target number to the front (if it is not already there)
            if (idx > 0) {
                flip(arr, idx + 1);
                ans.add(idx + 1);
            }

            // Flip it to its final position
            flip(arr, size);
            ans.add(size);
        }

        return ans;
    }

    private void flip(int[] arr, int k) {
        int left = 0, right = k - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}