class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + fruits[i][1];
        }
        int res = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            while (left <= right) {
                int l = fruits[left][0], r = fruits[right][0];
                int dist;
                if (r <= startPos) {
                    dist = startPos - l;
                } else if (l >= startPos) {
                    dist = r - startPos;
                } else {
                    dist = Math.min(startPos - l, r - startPos) + (r - l);
                }
                if (dist <= k) break;
                left++;
            }
            res = Math.max(res, prefix[right + 1] - prefix[left]);
        }
        return res;
    }
}