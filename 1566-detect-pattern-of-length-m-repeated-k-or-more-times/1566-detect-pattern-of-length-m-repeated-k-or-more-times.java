class Solution {
    public boolean containsPattern(int[] arr, int m, int k) {
        int n = arr.length;
        // Need at least m * k elements to fit the pattern
        if (n < m * k) return false;

        for (int i = 0; i + m * k <= n; i++) {
            boolean match = true;
            for (int j = i; j < i + m * (k - 1); j++) {
                if (arr[j] != arr[j + m]) {
                    match = false;
                    break;
                }
            }
            if (match) return true;
        }
        return false;
    }
}