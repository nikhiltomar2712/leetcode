class Solution {
    public int minimumLengthEncoding(String[] words) {
        TrieNode root = new TrieNode();

        // Insert all words in reverse
        for (String word : words) {
            TrieNode node = root;
            for (int i = word.length() - 1; i >= 0; i--) {
                int idx = word.charAt(i) - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
        }

        // Sum lengths of all leaves (+1 for '#')
        return dfs(root, 1);
    }

    private int dfs(TrieNode node, int depth) {
        boolean isLeaf = true;
        int sum = 0;
        for (TrieNode child : node.children) {
            if (child != null) {
                isLeaf = false;
                sum += dfs(child, depth + 1);
            }
        }
        // Only count leaf nodes
        return isLeaf ? depth : sum;
    }

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
    }
}