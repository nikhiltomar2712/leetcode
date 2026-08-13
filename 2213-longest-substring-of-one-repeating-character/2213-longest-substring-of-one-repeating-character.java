class Solution {
    // Segment tree node storing all necessary info
    static class Node {
        char leftChar, rightChar;
        int prefixLen, suffixLen, bestLen;
        int len; // length of the segment, optional but useful

        Node(char c) {
            this.leftChar = this.rightChar = c;
            this.prefixLen = this.suffixLen = this.bestLen = 1;
            this.len = 1;
        }

        Node() {
            // dummy constructor for merging
        }
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        arr = s.toCharArray();
        tree = new Node[4 * n];
        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char newChar = queryCharacters.charAt(i);
            // Only update if the character actually changes
            if (arr[idx] != newChar) {
                arr[idx] = newChar;
                update(1, 0, n - 1, idx, newChar);
            }
            ans[i] = tree[1].bestLen;
        }
        return ans;
    }

    // Build segment tree
    private void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(arr[l]);
            return;
        }
        int mid = (l + r) / 2;
        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Point update
    private void update(int node, int l, int r, int idx, char newChar) {
        if (l == r) {
            tree[node] = new Node(newChar);
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) {
            update(node * 2, l, mid, idx, newChar);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, newChar);
        }
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two nodes (left and right segments)
    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.len = left.len + right.len;
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        // Prefix: either left's entire prefix, or if left is all same char and matches right's left, extend
        res.prefixLen = left.prefixLen;
        if (left.prefixLen == left.len && left.rightChar == right.leftChar) {
            res.prefixLen = left.len + right.prefixLen;
        }

        // Suffix: similar logic
        res.suffixLen = right.suffixLen;
        if (right.suffixLen == right.len && right.leftChar == left.rightChar) {
            res.suffixLen = right.len + left.suffixLen;
        }

        // Best: max of left.best, right.best, and the merged middle run
        res.bestLen = Math.max(left.bestLen, right.bestLen);
        if (left.rightChar == right.leftChar) {
            res.bestLen = Math.max(res.bestLen, left.suffixLen + right.prefixLen);
        }

        return res;
    }
}