import java.util.*;

class Solution {
    public boolean isPrintable(int[][] targetGrid) {
        int rows = targetGrid.length;
        int cols = targetGrid[0].length;
        int MAX_COLOR = 61; // colors are 1..60

        int[] minR = new int[MAX_COLOR];
        int[] maxR = new int[MAX_COLOR];
        int[] minC = new int[MAX_COLOR];
        int[] maxC = new int[MAX_COLOR];
        Arrays.fill(minR, Integer.MAX_VALUE);
        Arrays.fill(minC, Integer.MAX_VALUE);
        Arrays.fill(maxR, -1);
        Arrays.fill(maxC, -1);

        boolean[] present = new boolean[MAX_COLOR];

        // Compute bounding boxes
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int c = targetGrid[i][j];
                present[c] = true;
                minR[c] = Math.min(minR[c], i);
                maxR[c] = Math.max(maxR[c], i);
                minC[c] = Math.min(minC[c], j);
                maxC[c] = Math.max(maxC[c], j);
            }
        }

        // Build graph: edge c -> d if color c's bounding box contains a cell of color d
        Set<Integer>[] graph = new HashSet[MAX_COLOR];
        for (int i = 0; i < MAX_COLOR; i++) graph[i] = new HashSet<>();
        int[] indegree = new int[MAX_COLOR];

        for (int c = 0; c < MAX_COLOR; c++) {
            if (!present[c]) continue;
            Set<Integer> deps = new HashSet<>();
            for (int i = minR[c]; i <= maxR[c]; i++) {
                for (int j = minC[c]; j <= maxC[c]; j++) {
                    int d = targetGrid[i][j];
                    if (d != c && !deps.contains(d)) {
                        deps.add(d);
                        if (graph[c].add(d)) {
                            indegree[d]++;
                        }
                    }
                }
            }
        }

        // Kahn's topological sort on present colors
        Deque<Integer> queue = new ArrayDeque<>();
        int presentCount = 0;
        for (int c = 0; c < MAX_COLOR; c++) {
            if (present[c]) {
                presentCount++;
                if (indegree[c] == 0) queue.add(c);
            }
        }

        int processed = 0;
        while (!queue.isEmpty()) {
            int c = queue.poll();
            processed++;
            for (int d : graph[c]) {
                if (--indegree[d] == 0) queue.add(d);
            }
        }

        return processed == presentCount;
    }
}