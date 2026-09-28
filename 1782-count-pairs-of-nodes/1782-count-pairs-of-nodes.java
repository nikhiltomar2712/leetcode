class Solution {
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] degree = new int[n + 1];
        Map<Long, Integer> shared = new HashMap<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            degree[u]++;
            degree[v]++;
            int a = Math.min(u, v), b = Math.max(u, v);
            long key = (long) a * (n + 1) + b;
            shared.put(key, shared.getOrDefault(key, 0) + 1);
        }
        int[] sorted = degree.clone();
        Arrays.sort(sorted);
        int[] res = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int q = queries[qi];
            int count = 0;
            int left = 1, right = n;
            while (left < right) {
                if (sorted[left] + sorted[right] > q) {
                    count += right - left;
                    right--;
                } else {
                    left++;
                }
            }
            for (Map.Entry<Long, Integer> entry : shared.entrySet()) {
                long key = entry.getKey();
                int u = (int) (key / (n + 1));
                int v = (int) (key % (n + 1));
                if (degree[u] + degree[v] > q && degree[u] + degree[v] - entry.getValue() <= q) {
                    count--;
                }
            }
            res[qi] = count;
        }
        return res;
    }
}