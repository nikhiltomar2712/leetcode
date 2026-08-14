class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int rectangleArea(int[][] rectangles) {
        int n = rectangles.length;
        
        // Collect all unique x and y coordinates
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        
        for (int[] rect : rectangles) {
            xs.add(rect[0]);
            xs.add(rect[2]);
            ys.add(rect[1]);
            ys.add(rect[3]);
        }
        
        // Sort and deduplicate coordinates
        Collections.sort(xs);
        Collections.sort(ys);
        xs = deduplicate(xs);
        ys = deduplicate(ys);
        
        // Map coordinates to indices
        Map<Integer, Integer> xIndex = new HashMap<>();
        Map<Integer, Integer> yIndex = new HashMap<>();
        for (int i = 0; i < xs.size(); i++) xIndex.put(xs.get(i), i);
        for (int i = 0; i < ys.size(); i++) yIndex.put(ys.get(i), i);
        
        // Create grid for counting coverage
        int m = xs.size(), nY = ys.size();
        boolean[][] covered = new boolean[m][nY];
        
        // Mark covered cells
        for (int[] rect : rectangles) {
            int x1 = xIndex.get(rect[0]);
            int x2 = xIndex.get(rect[2]);
            int y1 = yIndex.get(rect[1]);
            int y2 = yIndex.get(rect[3]);
            
            for (int i = x1; i < x2; i++) {
                for (int j = y1; j < y2; j++) {
                    covered[i][j] = true;
                }
            }
        }
        
        // Calculate total area
        long totalArea = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < nY - 1; j++) {
                if (covered[i][j]) {
                    long width = xs.get(i + 1) - xs.get(i);
                    long height = ys.get(j + 1) - ys.get(j);
                    totalArea = (totalArea + (width * height) % MOD) % MOD;
                }
            }
        }
        
        return (int) totalArea;
    }
    
    private List<Integer> deduplicate(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (int val : list) {
            if (result.isEmpty() || result.get(result.size() - 1) != val) {
                result.add(val);
            }
        }
        return result;
    }
}