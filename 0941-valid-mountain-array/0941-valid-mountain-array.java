class Solution {
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        if (n < 3) return false;
        
        int i = 0;
        
        // Climb up
        while (i + 1 < n && arr[i] < arr[i + 1]) {
            i++;
        }
        
        // Peak can't be the first or the last element
        if (i == 0 || i == n - 1) return false;
        
        // Climb down
        while (i + 1 < n && arr[i] > arr[i + 1]) {
            i++;
        }
        
        // We must have reached the end
        return i == n - 1;
    }
}