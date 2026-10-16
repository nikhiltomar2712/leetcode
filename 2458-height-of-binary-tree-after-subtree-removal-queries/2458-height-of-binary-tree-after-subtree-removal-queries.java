class Solution {
    Map<Integer, Integer> height = new HashMap<>();
    Map<Integer, Integer> level = new HashMap<>();
    Map<Integer, Integer> maxHeightAtLevel = new HashMap<>();
    Map<Integer, Integer> secondMaxHeightAtLevel = new HashMap<>();

    public int[] treeQueries(TreeNode root, int[] queries) {
        dfs(root, 0);
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int node = queries[i];
            int lvl = level.get(node);
            int h = height.get(node);
            if (maxHeightAtLevel.get(lvl) == h) {
                res[i] = lvl + secondMaxHeightAtLevel.getOrDefault(lvl, -1);
            } else {
                res[i] = lvl + maxHeightAtLevel.get(lvl);
            }
        }
        return res;
    }

    private int dfs(TreeNode node, int lvl) {
        if (node == null) return -1;
        level.put(node.val, lvl);
        int left = dfs(node.left, lvl + 1);
        int right = dfs(node.right, lvl + 1);
        int h = Math.max(left, right) + 1;
        height.put(node.val, h);
        if (h > maxHeightAtLevel.getOrDefault(lvl, -1)) {
            secondMaxHeightAtLevel.put(lvl, maxHeightAtLevel.getOrDefault(lvl, -1));
            maxHeightAtLevel.put(lvl, h);
        } else if (h > secondMaxHeightAtLevel.getOrDefault(lvl, -1)) {
            secondMaxHeightAtLevel.put(lvl, h);
        }
        return h;
    }
}