class Solution {
    private static final double EPS = 1e-6;

    public boolean judgePoint24(int[] cards) {
        List<Double> nums = new ArrayList<>();
        for (int card : cards) {
            nums.add((double) card);
        }
        return dfs(nums);
    }

    private boolean dfs(List<Double> nums) {
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - 24.0) < EPS;
        }

        int n = nums.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                List<Double> next = new ArrayList<>();
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) {
                        next.add(nums.get(k));
                    }
                }

                double a = nums.get(i);
                double b = nums.get(j);

                // Try all 4 operations (order matters for - and /)
                // a + b
                next.add(a + b);
                if (dfs(next)) return true;
                next.remove(next.size() - 1);

                // a - b
                next.add(a - b);
                if (dfs(next)) return true;
                next.remove(next.size() - 1);

                // a * b
                next.add(a * b);
                if (dfs(next)) return true;
                next.remove(next.size() - 1);

                // a / b (only if b != 0)
                if (Math.abs(b) > EPS) {
                    next.add(a / b);
                    if (dfs(next)) return true;
                    next.remove(next.size() - 1);
                }
            }
        }
        return false;
    }
}