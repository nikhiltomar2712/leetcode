class Solution {
    public int minAreaRect(int[][] points) {
        // Map from x → set of y's at that x
        Map<Integer, Set<Integer>> map = new HashMap<>();
        
        for (int[] p : points) {
            map.computeIfAbsent(p[0], k -> new HashSet<>()).add(p[1]);
        }
        
        int minArea = Integer.MAX_VALUE;
        List<Integer> xList = new ArrayList<>(map.keySet());
        
        // Check every pair of vertical lines (x1, x2)
        for (int i = 0; i < xList.size(); i++) {
            for (int j = i + 1; j < xList.size(); j++) {
                int x1 = xList.get(i);
                int x2 = xList.get(j);
                
                // Find common y-coordinates between the two vertical lines
                List<Integer> commonY = new ArrayList<>();
                for (int y : map.get(x1)) {
                    if (map.get(x2).contains(y)) {
                        commonY.add(y);
                    }
                }
                
                // Need at least 2 common y's to form a rectangle
                if (commonY.size() < 2) continue;
                
                Collections.sort(commonY);
                
                // Check consecutive pairs of y's
                for (int k = 1; k < commonY.size(); k++) {
                    int height = commonY.get(k) - commonY.get(k - 1);
                    int width = Math.abs(x2 - x1);
                    minArea = Math.min(minArea, height * width);
                }
            }
        }
        
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
}