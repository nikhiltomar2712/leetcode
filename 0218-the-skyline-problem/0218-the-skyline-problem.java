class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Create points for sweep line
        List<int[]> points = new ArrayList<>();
        for (int[] building : buildings) {
            // Start point: negative height indicates start
            points.add(new int[]{building[0], -building[2]});
            // End point: positive height indicates end
            points.add(new int[]{building[1], building[2]});
        }
        
        // Sort points:
        // 1. By x-coordinate
        // 2. If same x, process higher starts first (more negative),
        //    process lower ends first (smaller positive)
        Collections.sort(points, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        
        // Max heap to keep track of current heights
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        maxHeap.offer(0); // Ground level
        int prevMax = 0;
        
        for (int[] point : points) {
            int x = point[0];
            int height = point[1];
            
            if (height < 0) {
                // Start of building: add height
                maxHeap.offer(-height);
            } else {
                // End of building: remove height
                maxHeap.remove(height);
            }
            
            int currentMax = maxHeap.peek();
            
            // If max height changed, add key point
            if (currentMax != prevMax) {
                result.add(Arrays.asList(x, currentMax));
                prevMax = currentMax;
            }
        }
        
        return result;
    }
}