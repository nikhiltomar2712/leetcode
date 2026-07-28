class MapSum {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int sum = 0;               // sum of all values under this node
    }

    private TrieNode root;
    private Map<String, Integer> keyToVal;

    public MapSum() {
        root = new TrieNode();
        keyToVal = new HashMap<>();
    }

    public void insert(String key, int val) {
        int delta = val - keyToVal.getOrDefault(key, 0);
        keyToVal.put(key, val);

        TrieNode node = root;
        for (char c : key.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
            node.sum += delta;          // update the path with the difference
        }
    }

    public int sum(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                return 0;
            }
            node = node.children[idx];
        }
        return node.sum;
    }
}