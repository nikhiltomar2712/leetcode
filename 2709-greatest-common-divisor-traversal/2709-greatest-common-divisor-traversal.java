class Solution {
    public boolean canTraverseAllPairs(int[] nums) {
        int n = nums.length;
        if (n == 1) return true;
        for (int num : nums) {
            if (num == 1) return false;
        }
        Map<Integer, Integer> parent = new HashMap<>();
        for (int num : nums) {
            int x = num;
            for (int p = 2; p * p <= x; p++) {
                if (x % p == 0) {
                    union(parent, num, p);
                    while (x % p == 0) x /= p;
                }
            }
            if (x > 1) union(parent, num, x);
        }
        Set<Integer> roots = new HashSet<>();
        for (int num : nums) {
            roots.add(find(parent, num));
        }
        return roots.size() == 1;
    }

    private int find(Map<Integer, Integer> parent, int x) {
        parent.putIfAbsent(x, x);
        while (parent.get(x) != x) {
            parent.put(x, parent.get(parent.get(x)));
            x = parent.get(x);
        }
        return x;
    }

    private void union(Map<Integer, Integer> parent, int a, int b) {
        int ra = find(parent, a), rb = find(parent, b);
        if (ra != rb) parent.put(ra, rb);
    }
}