class Solution {
    public int[] countPoints(int[][] points, int[][] queries) {
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0], y = queries[i][1], r = queries[i][2];
            int r2 = r * r;
            int count = 0;
            for (int[] p : points) {
                int dx = p[0] - x, dy = p[1] - y;
                if (dx * dx + dy * dy <= r2) count++;
            }
            res[i] = count;
        }
        return res;
    }
}