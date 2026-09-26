class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[2];
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        int n = queries.length;
        int[][] q = new int[n][3];
        for (int i = 0; i < n; i++) {
            q[i][0] = queries[i][0];
            q[i][1] = queries[i][1];
            q[i][2] = i;
        }
        Arrays.sort(q, (a, b) -> a[1] - b[1]);

        int[] result = new int[n];
        TrieNode root = new TrieNode();
        int idx = 0;
        int m = nums.length;

        for (int i = 0; i < n; i++) {
            int x = q[i][0];
            int limit = q[i][1];
            int origIdx = q[i][2];

            while (idx < m && nums[idx] <= limit) {
                insert(root, nums[idx]);
                idx++;
            }

            if (idx == 0) {
                result[origIdx] = -1;
            } else {
                result[origIdx] = query(root, x);
            }
        }

        return result;
    }

    private void insert(TrieNode root, int num) {
        TrieNode node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (node.children[bit] == null) {
                node.children[bit] = new TrieNode();
            }
            node = node.children[bit];
        }
    }

    private int query(TrieNode root, int num) {
        TrieNode node = root;
        int maxXor = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            int desired = 1 - bit;
            if (node.children[desired] != null) {
                maxXor |= (1 << i);
                node = node.children[desired];
            } else {
                node = node.children[bit];
            }
        }
        return maxXor;
    }
}