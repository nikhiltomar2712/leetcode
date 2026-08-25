class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        if (n < 2) return n;

        int maxLen = 1;
        int currLen = 1;

        for (int i = 1; i < n; i++) {
            int cmp = Integer.compare(arr[i - 1], arr[i]);
            if (cmp == 0) {
                currLen = 1;
            } else if (i == 1 || cmp * Integer.compare(arr[i - 2], arr[i - 1]) < 0) {
                // Sign flipped (or start of a new potential turbulent sequence)
                currLen++;
            } else {
                // Same direction → restart from this pair
                currLen = 2;
            }
            maxLen = Math.max(maxLen, currLen);
        }
        return maxLen;
    }
}