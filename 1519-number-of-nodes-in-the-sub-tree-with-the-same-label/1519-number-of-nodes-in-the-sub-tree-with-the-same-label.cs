public class Solution {
    public int[] CountSubTrees(int n, int[][] edges, string labels) {
        // Build adjacency list
        var graph = new List<int>[n];
        for (int i = 0; i < n; i++) graph[i] = new List<int>();
        foreach (var e in edges) {
            graph[e[0]].Add(e[1]);
            graph[e[1]].Add(e[0]);
        }

        var ans = new int[n];
        Dfs(0, -1, graph, labels, ans);
        return ans;
    }

    private int[] Dfs(int node, int parent, List<int>[] graph, string labels, int[] ans) {
        int[] counts = new int[26];
        counts[labels[node] - 'a'] = 1;  // count self

        foreach (var child in graph[node]) {
            if (child == parent) continue;
            int[] childCounts = Dfs(child, node, graph, labels, ans);
            for (int i = 0; i < 26; i++)
                counts[i] += childCounts[i];
        }

        ans[node] = counts[labels[node] - 'a'];
        return counts;
    }
}