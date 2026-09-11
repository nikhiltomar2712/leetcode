class Solution {
    private int[] arr;
    private int n;
    private int d;
    private int[] memo;
    
    public int maxJumps(int[] arr, int d) {
        this.arr = arr;
        this.n = arr.length;
        this.d = d;
        this.memo = new int[n];
        Arrays.fill(memo, -1);
        
        int maxJumps = 0;
        for (int i = 0; i < n; i++) {
            maxJumps = Math.max(maxJumps, dfs(i));
        }
        return maxJumps;
    }
    
    private int dfs(int i) {
        if (memo[i] != -1) return memo[i];
        
        int best = 1; // at least the starting index itself
        
        // Jump right: from i+1 to i+d
        for (int j = i + 1; j <= Math.min(n - 1, i + d); j++) {
            if (arr[j] >= arr[i]) break; // can't jump over or to this
            best = Math.max(best, 1 + dfs(j));
        }
        
        // Jump left: from i-1 down to i-d
        for (int j = i - 1; j >= Math.max(0, i - d); j--) {
            if (arr[j] >= arr[i]) break; // can't jump over or to this
            best = Math.max(best, 1 + dfs(j));
        }
        
        memo[i] = best;
        return best;
    }
}