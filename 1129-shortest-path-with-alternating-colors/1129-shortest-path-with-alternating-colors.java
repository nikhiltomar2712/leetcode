class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // Build adjacency lists for red and blue edges
        List<Integer>[] redGraph = new List[n];
        List<Integer>[] blueGraph = new List[n];
        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }
        
        for (int[] edge : redEdges) {
            redGraph[edge[0]].add(edge[1]);
        }
        for (int[] edge : blueEdges) {
            blueGraph[edge[0]].add(edge[1]);
        }
        
        // Distance arrays: distRed[i] = shortest path to i ending with red edge
        // distBlue[i] = shortest path to i ending with blue edge
        int[] distRed = new int[n];
        int[] distBlue = new int[n];
        Arrays.fill(distRed, -1);
        Arrays.fill(distBlue, -1);
        
        // BFS queue: stores (node, color_of_last_edge)
        // color: 0 = red, 1 = blue
        Queue<int[]> queue = new LinkedList<>();
        
        // Start from node 0 with no last color
        // We can start with either red or blue edge
        distRed[0] = 0;
        distBlue[0] = 0;
        queue.offer(new int[]{0, 0}); // Start with red as last color (means next edge must be blue)
        queue.offer(new int[]{0, 1}); // Start with blue as last color (means next edge must be red)
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int lastColor = curr[1];
            int currentDist = (lastColor == 0) ? distRed[node] : distBlue[node];
            
            if (lastColor == 0) {
                // Last edge was RED, so next must be BLUE
                for (int next : blueGraph[node]) {
                    if (distBlue[next] == -1) {
                        distBlue[next] = currentDist + 1;
                        queue.offer(new int[]{next, 1});
                    }
                }
            } else {
                // Last edge was BLUE, so next must be RED
                for (int next : redGraph[node]) {
                    if (distRed[next] == -1) {
                        distRed[next] = currentDist + 1;
                        queue.offer(new int[]{next, 0});
                    }
                }
            }
        }
        
        // Build result: shortest of red and blue paths
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (distRed[i] == -1 && distBlue[i] == -1) {
                result[i] = -1;
            } else if (distRed[i] == -1) {
                result[i] = distBlue[i];
            } else if (distBlue[i] == -1) {
                result[i] = distRed[i];
            } else {
                result[i] = Math.min(distRed[i], distBlue[i]);
            }
        }
        return result;
    }
}