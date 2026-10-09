class Solution {
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, (a, b) -> a[0] - b[0]);
        int n = tiles.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (tiles[i][1] - tiles[i][0] + 1);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int end = tiles[i][0] + carpetLen - 1;
            int lo = i, hi = n - 1, j = i;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (tiles[mid][0] <= end) {
                    j = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            long covered = prefix[j + 1] - prefix[i];
            if (j + 1 < n && tiles[j + 1][0] <= end) {
                covered += end - tiles[j + 1][0] + 1;
            } else if (tiles[j][1] > end) {
                covered -= tiles[j][1] - end;
            }
            res = Math.max(res, (int) covered);
        }
        return res;
    }
}