public class Solution {
    public double GetMinDistSum(int[][] positions) {
        int n = positions.Length;
        if (n == 1) return 0.0;

        // Start at the centroid
        double x = 0, y = 0;
        foreach (var p in positions) {
            x += p[0];
            y += p[1];
        }
        x /= n;
        y /= n;

        double alpha = 0.5;          // learning rate
        double decay = 0.999;        // decay factor
        double eps = 1e-6;           // convergence threshold

        while (true) {
            double gradX = 0, gradY = 0;
            double distSum = 0;

            foreach (var p in positions) {
                double dx = x - p[0];
                double dy = y - p[1];
                double d = Math.Sqrt(dx * dx + dy * dy);

                // Avoid division by zero
                double inv = 1.0 / (d + 1e-8);
                gradX += dx * inv;
                gradY += dy * inv;
                distSum += d;
            }

            double stepX = gradX * alpha;
            double stepY = gradY * alpha;

            // Converged
            if (Math.Abs(stepX) <= eps && Math.Abs(stepY) <= eps) {
                return distSum;
            }

            x -= stepX;
            y -= stepY;
            alpha *= decay;
        }
    }
}