class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // Step 1: Count frequency of each element in arr1
        int[] frequency = new int[1001]; // Since constraints: 0 <= arr1[i], arr2[i] <= 1000
        for (int num : arr1) {
            frequency[num]++;
        }
        
        // Step 2: Build result array based on order in arr2
        int[] result = new int[arr1.length];
        int index = 0;
        
        for (int num : arr2) {
            while (frequency[num] > 0) {
                result[index++] = num;
                frequency[num]--;
            }
        }
        
        // Step 3: Add remaining elements in ascending order
        for (int i = 0; i < frequency.length; i++) {
            while (frequency[i] > 0) {
                result[index++] = i;
                frequency[i]--;
            }
        }
        
        return result;
    }
}