class Solution {
    private static final int BASE = 13;
    private Map<Long, Integer> memo = new HashMap<>();

    public int tilingRectangle(int n, int m) {
        // Always keep the smaller dimension as height for consistency
        if (n > m) {
            int tmp = n;
            n = m;
            m = tmp;
        }
        return dfs(n, m, new int[m]);
    }

    private int dfs(int n, int m, int[] heights) {
        long key = hash(heights);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int minH = Integer.MAX_VALUE;
        int start = -1;
        for (int i = 0; i < m; i++) {
            if (heights[i] < minH) {
                minH = heights[i];
                start = i;
            }
        }

        // Fully filled
        if (minH == n) {
            return 0;
        }

        int ans = n * m; // worst case (all 1×1)

        // Try every possible square size that can start at 'start'
        for (int size = 1; size <= Math.min(m - start, n - minH); size++) {
            // The next 'size' columns must all have the same height
            if (heights[start + size - 1] != minH) break;

            // Place the square
            for (int i = start; i < start + size; i++) {
                heights[i] += size;
            }

            ans = Math.min(ans, 1 + dfs(n, m, heights));

            // Backtrack
            for (int i = start; i < start + size; i++) {
                heights[i] -= size;
            }
        }

        memo.put(key, ans);
        return ans;
    }

    private long hash(int[] heights) {
        long h = 0;
        for (int i = heights.length - 1; i >= 0; i--) {
            h = h * BASE + heights[i];
        }
        return h;
    }
}