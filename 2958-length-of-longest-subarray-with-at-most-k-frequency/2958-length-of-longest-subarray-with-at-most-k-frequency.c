#include <stdlib.h>
#include <string.h>

#define HASH_SIZE 200003   // a prime larger than 1e5

typedef struct Node {
    int key;
    int count;
    struct Node* next;
} Node;

Node* hashTable[HASH_SIZE];

unsigned int hash(int key) {
    // simple hash for positive ints
    return ((unsigned int)key) % HASH_SIZE;
}

Node* find(int key) {
    unsigned int h = hash(key);
    Node* curr = hashTable[h];
    while (curr) {
        if (curr->key == key) return curr;
        curr = curr->next;
    }
    return NULL;
}

void insert_or_inc(int key) {
    Node* node = find(key);
    if (node) {
        node->count++;
        return;
    }
    unsigned int h = hash(key);
    node = (Node*)malloc(sizeof(Node));
    node->key = key;
    node->count = 1;
    node->next = hashTable[h];
    hashTable[h] = node;
}

void dec(int key) {
    unsigned int h = hash(key);
    Node* curr = hashTable[h];
    Node* prev = NULL;
    while (curr) {
        if (curr->key == key) {
            curr->count--;
            if (curr->count == 0) {
                if (prev) prev->next = curr->next;
                else hashTable[h] = curr->next;
                free(curr);
            }
            return;
        }
        prev = curr;
        curr = curr->next;
    }
}

int get_count(int key) {
    Node* node = find(key);
    return node ? node->count : 0;
}

void clear_hash() {
    for (int i = 0; i < HASH_SIZE; i++) {
        Node* curr = hashTable[i];
        while (curr) {
            Node* tmp = curr;
            curr = curr->next;
            free(tmp);
        }
        hashTable[i] = NULL;
    }
}

int maxSubarrayLength(int* nums, int numsSize, int k) {
    // clear hash table
    memset(hashTable, 0, sizeof(hashTable));
    
    int left = 0;
    int maxLen = 0;
    
    for (int right = 0; right < numsSize; right++) {
        insert_or_inc(nums[right]);
        
        // shrink while frequency of nums[right] > k
        while (get_count(nums[right]) > k) {
            dec(nums[left]);
            left++;
        }
        
        int len = right - left + 1;
        if (len > maxLen) maxLen = len;
    }
    
    clear_hash();
    return maxLen;
}