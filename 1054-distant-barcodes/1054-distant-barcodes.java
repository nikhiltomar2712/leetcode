class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;
        int[] count = new int[10001];
        int maxFreq = 0;
        int maxNum = 0;

        // Count frequencies
        for (int b : barcodes) {
            count[b]++;
            if (count[b] > maxFreq) {
                maxFreq = count[b];
                maxNum = b;
            }
        }

        int[] ans = new int[n];
        int idx = 0; // start filling even positions

        // Helper to place a number
        // First place the most frequent number
        while (count[maxNum]-- > 0) {
            ans[idx] = maxNum;
            idx += 2;
            if (idx >= n) idx = 1; // switch to odd positions
        }

        // Place the rest of the numbers
        for (int num = 1; num <= 10000; num++) {
            while (count[num]-- > 0) {
                ans[idx] = num;
                idx += 2;
                if (idx >= n) idx = 1;
            }
        }

        return ans;
    }
}