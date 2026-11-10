class Solution {
    class TrieNode {
        // Key: combined integer for character pair (front, back)
        // Using HashMap since only 26*26 = 676 possible pairs, and not all appear
        Map<Integer, TrieNode> children = new HashMap<>();
        int count = 0; // number of words that passed through this node
    }

    public long countPrefixSuffixPairs(String[] words) {
        long ans = 0;
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode node = root;
            int len = word.length();

            // Traverse the trie using paired characters
            for (int i = 0; i < len; i++) {
                // Encode (front char, back char) into a single integer
                int front = word.charAt(i) - 'a';
                int back = word.charAt(len - 1 - i) - 'a';
                int key = front * 26 + back;

                node.children.putIfAbsent(key, new TrieNode());
                node = node.children.get(key);

                // All words that have reached this node are prefix-suffix matches
                // for the current word up to position i
                ans += node.count;
            }

            // Mark that this word ends here
            node.count++;
        }

        return ans;
    }
}