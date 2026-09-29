class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int m = restrictions.length;
        int[][] r = new int[m + 2][2];
        r[0] = new int[]{1, 0};
        for (int i = 0; i < m; i++) r[i + 1] = restrictions[i];
        r[m + 1] = new int[]{n, n - 1};
        Arrays.sort(r, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < r.length; i++) {
            r[i][1] = Math.min(r[i][1], r[i - 1][1] + r[i][0] - r[i - 1][0]);
        }
        for (int i = r.length - 2; i >= 0; i--) {
            r[i][1] = Math.min(r[i][1], r[i + 1][1] + r[i + 1][0] - r[i][0]);
        }
        int res = 0;
        for (int i = 1; i < r.length; i++) {
            int dist = r[i][0] - r[i - 1][0];
            int h1 = r[i - 1][1], h2 = r[i][1];
            res = Math.max(res, Math.max(h1, h2) + (dist - Math.abs(h1 - h2)) / 2);
        }
        return res;
    }
}