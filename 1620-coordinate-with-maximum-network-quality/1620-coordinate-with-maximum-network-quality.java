class Solution {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int maxQuality = -1;
        int bestX = 0, bestY = 0;

        // Coordinates are bounded by [0, 50] per constraints,
        // but we also need to consider 0,0 as a default.
        // Expand range to safely cover all possible contributing points.
        int minX = 0, maxX = 50;
        int minY = 0, maxY = 50;

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                int quality = 0;
                for (int[] t : towers) {
                    int dx = x - t[0], dy = y - t[1];
                    double dist = Math.sqrt(dx * dx + dy * dy);
                    if (dist <= radius) {
                        quality += (int) Math.floor(t[2] / (1 + dist));
                    }
                }

                if (quality > maxQuality) {
                    maxQuality = quality;
                    bestX = x;
                    bestY = y;
                }
            }
        }

        return new int[]{bestX, bestY};
    }
}