class Solution {
    public int[] movesToStamp(String stamp, String target) {
        char[] S = stamp.toCharArray();
        char[] T = target.toCharArray();
        int n = T.length, m = S.length;
        
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[n]; // whether we already stamped at this position
        int stars = 0;                      // number of '?' we have created
        
        // Keep trying to unstamp until the whole string becomes '?'
        while (stars < n) {
            boolean stamped = false;
            
            for (int i = 0; i <= n - m; i++) {
                if (!visited[i] && canStamp(T, i, S)) {
                    // "Unstamp" this position (replace matching characters with '?')
                    stars = doStamp(T, i, m, stars);
                    visited[i] = true;
                    res.add(i);
                    stamped = true;
                    
                    if (stars == n) break;
                }
            }
            
            // If we couldn't stamp anything in a full pass → impossible
            if (!stamped) return new int[0];
        }
        
        // The order we found is reverse of the actual stamping order
        Collections.reverse(res);
        
        // Convert to array
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
    
    // Check if we can stamp (or unstamp) at position start
    private boolean canStamp(char[] T, int start, char[] S) {
        for (int i = 0; i < S.length; i++) {
            if (T[start + i] != '?' && T[start + i] != S[i]) {
                return false;
            }
        }
        return true;
    }
    
    // Replace the matched segment with '?' and return new number of stars
    private int doStamp(char[] T, int start, int m, int stars) {
        for (int i = 0; i < m; i++) {
            if (T[start + i] != '?') {
                T[start + i] = '?';
                stars++;
            }
        }
        return stars;
    }
}