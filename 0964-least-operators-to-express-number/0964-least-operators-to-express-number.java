class Solution {
    private int x;
    private Map<Integer, Integer> memo = new HashMap<>();
    
    public int leastOpsExpressTarget(int x, int target) {
        this.x = x;
        return dfs(target);
    }
    
    private int dfs(int v) {
        if (x >= v) {
            // v * (x/x)  or  x - (x-v)*(x/x)
            return Math.min(v * 2 - 1, 2 * (x - v));
        }
        
        if (memo.containsKey(v)) {
            return memo.get(v);
        }
        
        // Find the smallest power x^k >= v
        int k = 2;
        long y = (long) x * x;          // x^2
        while (y < v) {
            y *= x;
            k++;
        }
        
        // Option 1: x^{k-1} + remainder
        int ans = (k - 1) + dfs(v - (int)(y / x));
        
        // Option 2: x^k - (x^k - v)   (only useful when the remainder is smaller)
        if (y - v < v) {
            ans = Math.min(ans, k + dfs((int)(y - v)));
        }
        
        memo.put(v, ans);
        return ans;
    }
}