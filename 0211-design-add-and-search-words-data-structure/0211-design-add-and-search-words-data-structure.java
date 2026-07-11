class WordDictionary {
    private TrieNode root;
    
    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isWord = true;
    }
    
    public boolean search(String word) {
        return searchInNode(word, 0, root);
    }
    
    private boolean searchInNode(String word, int index, TrieNode node) {
        if (index == word.length()) {
            return node.isWord;
        }
        
        char c = word.charAt(index);
        
        if (c == '.') {
            // Try all possible characters
            for (TrieNode child : node.children) {
                if (child != null && searchInNode(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            // Specific character
            int childIndex = c - 'a';
            TrieNode child = node.children[childIndex];
            if (child == null) {
                return false;
            }
            return searchInNode(word, index + 1, child);
        }
    }
    
    private class TrieNode {
        TrieNode[] children;
        boolean isWord;
        
        TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }
}