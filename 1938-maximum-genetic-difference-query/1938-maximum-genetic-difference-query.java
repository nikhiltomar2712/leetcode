class Solution {
    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        int n = parents.length;
        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (parents[i] == -1) root = i;
            else children[parents[i]].add(i);
        }
        Map<Integer, List<int[]>> queryMap = new HashMap<>();
        for (int i = 0; i < queries.length; i++) {
            queryMap.computeIfAbsent(queries[i][0], k -> new ArrayList<>()).add(new int[]{queries[i][1], i});
        }
        int[] res = new int[queries.length];
        Trie trie = new Trie();
        dfs(root, children, queryMap, trie, res);
        return res;
    }

    private void dfs(int node, List<Integer>[] children, Map<Integer, List<int[]>> queryMap, Trie trie, int[] res) {
        trie.insert(node);
        if (queryMap.containsKey(node)) {
            for (int[] q : queryMap.get(node)) {
                res[q[1]] = trie.query(q[0]);
            }
        }
        for (int child : children[node]) {
            dfs(child, children, queryMap, trie, res);
        }
        trie.remove(node);
    }

    class Trie {
        Trie[] children = new Trie[2];
        int count;

        void insert(int num) {
            Trie node = this;
            for (int i = 17; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit] == null) node.children[bit] = new Trie();
                node = node.children[bit];
                node.count++;
            }
        }

        void remove(int num) {
            Trie node = this;
            for (int i = 17; i >= 0; i--) {
                int bit = (num >> i) & 1;
                node = node.children[bit];
                node.count--;
            }
        }

        int query(int num) {
            Trie node = this;
            int res = 0;
            for (int i = 17; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit ^ 1] != null && node.children[bit ^ 1].count > 0) {
                    res |= (1 << i);
                    node = node.children[bit ^ 1];
                } else {
                    node = node.children[bit];
                }
            }
            return res;
        }
    }
}