class Solution {
    public int countPairs(int[] nums, int low, int high) {
        return count(nums, high) - count(nums, low - 1);
    }

    private int count(int[] nums, int limit) {
        Trie trie = new Trie();
        int res = 0;
        for (int num : nums) {
            res += trie.query(num, limit);
            trie.insert(num);
        }
        return res;
    }

    class Trie {
        Trie[] children = new Trie[2];
        int count;

        void insert(int num) {
            Trie node = this;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new Trie();
                }
                node = node.children[bit];
                node.count++;
            }
        }

        int query(int num, int limit) {
            Trie node = this;
            int res = 0;
            for (int i = 31; i >= 0 && node != null; i--) {
                int bit = (num >> i) & 1;
                int limitBit = (limit >> i) & 1;
                if (limitBit == 1) {
                    if (node.children[bit] != null) {
                        res += node.children[bit].count;
                    }
                    node = node.children[bit ^ 1];
                } else {
                    node = node.children[bit];
                }
            }
            if (node != null) {
                res += node.count;
            }
            return res;
        }
    }
}