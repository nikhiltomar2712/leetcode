class Solution {
    private int[] parent;

    public boolean gcdSort(int[] nums) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        parent = new int[max + 1];
        for (int i = 0; i <= max; i++) parent[i] = i;

        for (int num : nums) {
            int x = num;
            for (int p = 2; p * p <= x; p++) {
                if (x % p == 0) {
                    union(num, p);
                    while (x % p == 0) x /= p;
                }
            }
            if (x > 1) union(num, x);
        }

        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        for (int i = 0; i < nums.length; i++) {
            if (find(nums[i]) != find(sorted[i])) return false;
        }
        return true;
    }

    private int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    private void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}