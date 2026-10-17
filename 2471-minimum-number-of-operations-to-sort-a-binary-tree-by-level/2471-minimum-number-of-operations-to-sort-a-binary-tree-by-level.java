class Solution {
    public int minimumOperations(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] level = new int[size];
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level[i] = node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res += minSwaps(level);
        }
        return res;
    }

    private int minSwaps(int[] arr) {
        int n = arr.length;
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.put(sorted[i], i);
        }
        int[] target = new int[n];
        for (int i = 0; i < n; i++) {
            target[i] = pos.get(arr[i]);
        }
        boolean[] visited = new boolean[n];
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            int cycleLen = 0;
            int cur = i;
            while (!visited[cur]) {
                visited[cur] = true;
                cur = target[cur];
                cycleLen++;
            }
            if (cycleLen > 1) swaps += cycleLen - 1;
        }
        return swaps;
    }
}