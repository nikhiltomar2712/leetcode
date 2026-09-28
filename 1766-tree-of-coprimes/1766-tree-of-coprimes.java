class Solution {
    public int[] getCoprimes(int[] nums, int[][] edges) {
        int n = nums.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        int[][] last = new int[51][2];
        for (int[] row : last) Arrays.fill(row, -1);
        dfs(0, -1, 0, nums, graph, res, last);
        return res;
    }

    private void dfs(int node, int parent, int depth, int[] nums, List<Integer>[] graph, int[] res, int[][] last) {
        int val = nums[node];
        for (int i = 1; i <= 50; i++) {
            if (last[i][0] != -1 && gcd(val, i) == 1) {
                if (res[node] == -1 || last[i][1] > last[nums[res[node]]][1]) {
                    res[node] = last[i][0];
                }
            }
        }
        int[] prev = last[val];
        last[val] = new int[]{node, depth};
        for (int next : graph[node]) {
            if (next != parent) {
                dfs(next, node, depth + 1, nums, graph, res, last);
            }
        }
        last[val] = prev;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}