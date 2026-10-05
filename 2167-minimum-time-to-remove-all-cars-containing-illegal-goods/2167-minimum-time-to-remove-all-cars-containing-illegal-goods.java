class Solution {
    public int minimumTime(String s) {
        int n = s.length();
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                left[i + 1] = Math.min(left[i] + 2, i + 1);
            } else {
                left[i + 1] = left[i];
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                right[i] = Math.min(right[i + 1] + 2, n - i);
            } else {
                right[i] = right[i + 1];
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            res = Math.min(res, left[i] + right[i]);
        }
        return res;
    }
}