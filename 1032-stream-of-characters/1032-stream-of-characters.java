class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord = false;
}

class StreamChecker {
    private TrieNode root = new TrieNode();
    private StringBuilder stream = new StringBuilder();

    public StreamChecker(String[] words) {
        for (String word : words) {
            insert(word);
        }
    }
    
    public boolean query(char letter) {
        stream.append(letter);
        TrieNode node = root;
        
        // Walk the stream from the end (most recent character) towards the beginning
        for (int i = stream.length() - 1; i >= 0; i--) {
            int idx = stream.charAt(i) - 'a';
            if (node.children[idx] == null) {
                return false;
            }
            node = node.children[idx];
            if (node.isWord) {
                return true;   // found a matching suffix
            }
        }
        return false;
    }
    
    // Insert the word in reverse order so that suffixes become prefixes in the Trie
    private void insert(String word) {
        TrieNode node = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            int idx = word.charAt(i) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isWord = true;
    }
}