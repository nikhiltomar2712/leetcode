import java.util.*;

class Solution {
    private int[] parent;

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Union all swapable indices
        for (List<Integer> pair : pairs) {
            union(pair.get(0), pair.get(1));
        }

        // Group characters by their connected component (root)
        Map<Integer, PriorityQueue<Character>> components = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            components.computeIfAbsent(root, k -> new PriorityQueue<>()).offer(s.charAt(i));
        }

        // Build the result by always taking the smallest available character
        // from the component of the current position
        char[] result = new char[n];
        for (int i = 0; i < n; i++) {
            int root = find(i);
            result[i] = components.get(root).poll();
        }

        return new String(result);
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // path compression
        }
        return parent[x];
    }

    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) {
            parent[px] = py;
        }
    }
}