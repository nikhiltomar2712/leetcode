#define ALPHABET 27   // a-z + '{'
#define MAXN 10005

typedef struct TrieNode {
    struct TrieNode* children[ALPHABET];
    int index;          // largest index ending here
} TrieNode;

typedef struct {
    TrieNode* root;
} WordFilter;

TrieNode* createNode() {
    TrieNode* node = (TrieNode*)malloc(sizeof(TrieNode));
    for (int i = 0; i < ALPHABET; i++) {
        node->children[i] = NULL;
    }
    node->index = -1;
    return node;
}

void insert(TrieNode* root, const char* s, int idx) {
    TrieNode* node = root;
    for (int i = 0; s[i]; i++) {
        int c = (s[i] == '{') ? 26 : s[i] - 'a';
        if (!node->children[c]) {
            node->children[c] = createNode();
        }
        node = node->children[c];
        // keep the largest index along the path
        if (idx > node->index) {
            node->index = idx;
        }
    }
}

int search(TrieNode* root, const char* s) {
    TrieNode* node = root;
    for (int i = 0; s[i]; i++) {
        int c = (s[i] == '{') ? 26 : s[i] - 'a';
        if (!node->children[c]) {
            return -1;
        }
        node = node->children[c];
    }
    return node->index;
}

WordFilter* wordFilterCreate(char** words, int wordsSize) {
    WordFilter* obj = (WordFilter*)malloc(sizeof(WordFilter));
    obj->root = createNode();
    
    for (int i = 0; i < wordsSize; i++) {
        char* word = words[i];
        int len = strlen(word);
        
        // Insert all possible "suffix{word"
        // This allows querying "suff{pref"
        for (int j = 0; j <= len; j++) {
            char key[20];
            // suffix starting at j + '{' + whole word
            sprintf(key, "%s{%s", word + j, word);
            insert(obj->root, key, i);
        }
    }
    
    return obj;
}

int wordFilterF(WordFilter* obj, char* pref, char* suff) {
    char key[20];
    sprintf(key, "%s{%s", suff, pref);
    return search(obj->root, key);
}

void freeTrie(TrieNode* node) {
    if (!node) return;
    for (int i = 0; i < ALPHABET; i++) {
        freeTrie(node->children[i]);
    }
    free(node);
}

void wordFilterFree(WordFilter* obj) {
    freeTrie(obj->root);
    free(obj);
}