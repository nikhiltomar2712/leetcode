class Solution {
    public int numPoints(int[][] darts, int r) {
        int n = darts.length;
        int max = 1;
        int r2 = r * r;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = darts[i][0], y1 = darts[i][1];
                int x2 = darts[j][0], y2 = darts[j][1];

                double dx = x2 - x1, dy = y2 - y1;
                double d2 = dx * dx + dy * dy;
                double d = Math.sqrt(d2);

                if (d > 2 * r) continue;

                double mx = (x1 + x2) / 2.0, my = (y1 + y2) / 2.0;
                double h = Math.sqrt(Math.max(0, r2 - d2 / 4.0));

                double cx1 = mx - h * dy / d;
                double cy1 = my + h * dx / d;
                double cx2 = mx + h * dy / d;
                double cy2 = my - h * dx / d;

                max = Math.max(max, count(darts, cx1, cy1, r2));
                max = Math.max(max, count(darts, cx2, cy2, r2));
            }
        }

        return max;
    }

    private int count(int[][] darts, double cx, double cy, int r2) {
        int c = 0;
        for (int[] p : darts) {
            double dx = p[0] - cx, dy = p[1] - cy;
            if (dx * dx + dy * dy <= r2 + 1e-7) c++;
        }
        return c;
    }
}