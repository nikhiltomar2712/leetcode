class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] swap : allowedSwaps) {
            union(parent, swap[0], swap[1]);
        }

        Map<Integer, Map<Integer, Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(parent, i);
            groups.computeIfAbsent(root, k -> new HashMap<>())
                  .merge(source[i], 1, Integer::sum);
        }

        int distance = 0;
        for (int i = 0; i < n; i++) {
            int root = find(parent, i);
            Map<Integer, Integer> count = groups.get(root);
            if (count.getOrDefault(target[i], 0) > 0) {
                count.put(target[i], count.get(target[i]) - 1);
            } else {
                distance++;
            }
        }

        return distance;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private void union(int[] parent, int a, int b) {
        int ra = find(parent, a);
        int rb = find(parent, b);
        if (ra != rb) parent[ra] = rb;
    }
}