class Solution {
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int remove = n / 20;          // 5% of the elements
        
        double sum = 0;
        for (int i = remove; i < n - remove; i++) {
            sum += arr[i];
        }
        
        return sum / (n - 2 * remove);  // remaining length = 90% of n
    }
}