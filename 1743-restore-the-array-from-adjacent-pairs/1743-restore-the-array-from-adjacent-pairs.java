import java.util.*;

class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            graph.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
            graph.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
        }

        // Find the start: a node with only one neighbor (an endpoint)
        int start = 0;
        for (Map.Entry<Integer, List<Integer>> e : graph.entrySet()) {
            if (e.getValue().size() == 1) {
                start = e.getKey();
                break;
            }
        }

        int n = adjacentPairs.length + 1;
        int[] result = new int[n];
        result[0] = start;
        result[1] = graph.get(start).get(0);

        for (int i = 2; i < n; i++) {
            List<Integer> neighbors = graph.get(result[i - 1]);
            result[i] = (neighbors.get(0) == result[i - 2]) ? neighbors.get(1) : neighbors.get(0);
        }

        return result;
    }
}