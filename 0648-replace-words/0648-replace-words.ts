class TrieNode {
    children: (TrieNode | null)[] = new Array(26).fill(null);
    isEnd: boolean = false;
}

class Trie {
    private root = new TrieNode();

    insert(word: string): void {
        let node = this.root;
        for (const c of word) {
            const idx = c.charCodeAt(0) - 97;
            if (!node.children[idx]) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx]!;
        }
        node.isEnd = true;
    }

    // Returns the shortest root that is a prefix of word, or the word itself
    search(word: string): string {
        let node = this.root;
        for (let i = 0; i < word.length; i++) {
            const idx = word.charCodeAt(i) - 97;
            if (!node.children[idx]) {
                return word;
            }
            node = node.children[idx]!;
            if (node.isEnd) {
                return word.slice(0, i + 1);
            }
        }
        return word;
    }
}

function replaceWords(dictionary: string[], sentence: string): string {
    const trie = new Trie();
    for (const root of dictionary) {
        trie.insert(root);
    }

    return sentence
        .split(" ")
        .map(word => trie.search(word))
        .join(" ");
}