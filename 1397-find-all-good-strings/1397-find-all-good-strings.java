class Solution {
    private static final int MOD = 1_000_000_007;
    private int n, m;
    private String s1, s2, evil;
    private int[][] nextState;   // KMP automaton: nextState[state][charIdx]
    private int[][][][] memo;
    private boolean[][][][] visited;
    
    public int findGoodStrings(int n, String s1, String s2, String evil) {
        this.n = n;
        this.s1 = s1;
        this.s2 = s2;
        this.evil = evil;
        this.m = evil.length();
        
        // Build KMP automaton
        buildKMP();
        
        // memo[pos][kmpState][tightLow][tightHigh]
        memo = new int[n][m][2][2];
        visited = new boolean[n][m][2][2];
        
        return dp(0, 0, 1, 1);
    }
    
    private void buildKMP() {
        int[] fail = new int[m];
        for (int i = 1; i < m; i++) {
            int j = fail[i - 1];
            while (j > 0 && evil.charAt(i) != evil.charAt(j)) j = fail[j - 1];
            if (evil.charAt(i) == evil.charAt(j)) j++;
            fail[i] = j;
        }
        
        nextState = new int[m][26];
        for (int state = 0; state < m; state++) {
            for (int c = 0; c < 26; c++) {
                char ch = (char) ('a' + c);
                int j = state;
                while (j > 0 && ch != evil.charAt(j)) j = fail[j - 1];
                if (ch == evil.charAt(j)) j++;
                nextState[state][c] = j;  // j == m means we hit evil
            }
        }
    }
    
    private int dp(int pos, int kmpState, int tightLow, int tightHigh) {
        if (pos == n) return 1;
        
        if (visited[pos][kmpState][tightLow][tightHigh]) {
            return memo[pos][kmpState][tightLow][tightHigh];
        }
        
        long result = 0;
        int lo = tightLow == 1 ? s1.charAt(pos) - 'a' : 0;
        int hi = tightHigh == 1 ? s2.charAt(pos) - 'a' : 25;
        
        for (int c = lo; c <= hi; c++) {
            int nextKmp = nextState[kmpState][c];
            if (nextKmp == m) continue;  // would contain evil
            
            int newTightLow = (tightLow == 1 && c == lo) ? 1 : 0;
            int newTightHigh = (tightHigh == 1 && c == hi) ? 1 : 0;
            
            result += dp(pos + 1, nextKmp, newTightLow, newTightHigh);
            result %= MOD;
        }
        
        visited[pos][kmpState][tightLow][tightHigh] = true;
        memo[pos][kmpState][tightLow][tightHigh] = (int) result;
        return (int) result;
    }
}