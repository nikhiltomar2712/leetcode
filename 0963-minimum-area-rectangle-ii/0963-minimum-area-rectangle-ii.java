class Solution {
    public double minAreaFreeRect(int[][] points) {
        int n = points.length;
        Set<Long> pointSet = new HashSet<>();
        for (int[] p : points) {
            pointSet.add(encode(p[0], p[1]));
        }
        
        double minArea = Double.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                int x2 = points[j][0], y2 = points[j][1];
                
                for (int k = j + 1; k < n; k++) {
                    if (k == i) continue;
                    int x3 = points[k][0], y3 = points[k][1];
                    
                    // Fourth point
                    int x4 = x2 + x3 - x1;
                    int y4 = y2 + y3 - y1;
                    
                    if (pointSet.contains(encode(x4, y4))) {
                        // Check if vectors are perpendicular
                        int dx1 = x2 - x1, dy1 = y2 - y1;
                        int dx2 = x3 - x1, dy2 = y3 - y1;
                        
                        if (dx1 * dx2 + dy1 * dy2 == 0) {  // dot product == 0
                            double side1 = Math.sqrt(1.0 * dx1 * dx1 + 1.0 * dy1 * dy1);
                            double side2 = Math.sqrt(1.0 * dx2 * dx2 + 1.0 * dy2 * dy2);
                            double area = side1 * side2;
                            if (area > 0) {
                                minArea = Math.min(minArea, area);
                            }
                        }
                    }
                }
            }
        }
        
        return minArea == Double.MAX_VALUE ? 0 : minArea;
    }
    
    private long encode(int x, int y) {
        return ((long) x << 20) | y;   // sufficient for the given constraints
    }
}