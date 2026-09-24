class Solution {
    private int[] tree;
    private int size;
    private static final int MOD = 1_000_000_007;

    public int createSortedArray(int[] instructions) {
        // Find max value to size the BIT
        int maxVal = 0;
        for (int v : instructions) maxVal = Math.max(maxVal, v);

        size = maxVal + 1;
        tree = new int[size + 1];

        long totalCost = 0;

        for (int i = 0; i < instructions.length; i++) {
            int v = instructions[i];

            // Count elements strictly less than v
            int costLeft = query(v - 1);

            // Count elements strictly greater than v
            // i = number of elements already inserted
            // query(v) = count of elements <= v
            int costRight = i - query(v);

            totalCost = (totalCost + Math.min(costLeft, costRight)) % MOD;

            // Insert v
            update(v, 1);
        }

        return (int) totalCost;
    }

    private void update(int i, int delta) {
        while (i < size + 1) {
            tree[i] += delta;
            i += i & -i;
        }
    }

    private int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & -i;
        }
        return sum;
    }
}