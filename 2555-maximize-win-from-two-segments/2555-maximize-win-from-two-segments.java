class Solution {
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        int[] left = new int[n + 1];
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (prizePositions[i] - prizePositions[j] > k) j++;
            left[i + 1] = Math.max(left[i], i - j + 1);
        }
        int[] right = new int[n + 1];
        j = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (prizePositions[j] - prizePositions[i] > k) j--;
            right[i] = Math.max(right[i + 1], j - i + 1);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, left[i + 1] + right[i + 1]);
        }
        return res;
    }
}