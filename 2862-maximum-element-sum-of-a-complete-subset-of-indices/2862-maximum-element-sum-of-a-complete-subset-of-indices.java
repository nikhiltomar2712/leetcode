class Solution {
    public long maximumSum(List<Integer> nums) {
        Map<Integer, Long> groups = new HashMap<>();
        long ans = 0;
        
        for (int i = 0; i < nums.size(); i++) {
            int core = squareFree(i + 1);
            long newSum = groups.getOrDefault(core, 0L) + nums.get(i);
            groups.put(core, newSum);
            ans = Math.max(ans, newSum);
        }
        return ans;
    }
    
    private int squareFree(int x) {
        for (int p = 2; (long) p * p <= x; p++) {
            int sq = p * p;
            while (x % sq == 0) {
                x /= sq;
            }
        }
        return x;
    }
}