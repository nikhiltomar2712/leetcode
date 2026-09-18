class Solution {
    public String minInteger(String num, int k) {
        int n = num.length();
        // Positions of each digit 0-9 (in order)
        Queue<Integer>[] pos = new Queue[10];
        for (int d = 0; d < 10; d++) pos[d] = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            pos[num.charAt(i) - '0'].offer(i);
        }

        Fenwick bit = new Fenwick(n);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            // Try digits 0..9 in order
            for (int d = 0; d < 10; d++) {
                if (pos[d].isEmpty()) continue;
                int idx = pos[d].peek();
                // Cost = original index - number of already-moved elements before idx
                int cost = idx - bit.query(idx);
                if (cost <= k) {
                    k -= cost;
                    pos[d].poll();
                    sb.append((char) ('0' + d));
                    bit.update(idx, 1);   // mark as moved
                    break;
                }
            }
        }
        return sb.toString();
    }

    // Fenwick tree for prefix sums
    static class Fenwick {
        int[] tree;
        int n;
        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }
        void update(int i, int delta) {
            for (i++; i <= n; i += i & -i) tree[i] += delta;
        }
        int query(int i) {  // sum of [0, i)
            int sum = 0;
            for (; i > 0; i -= i & -i) sum += tree[i];
            return sum;
        }
    }
}