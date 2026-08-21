class Solution {
    public String shortestSuperstring(String[] words) {
        int n = words.length;
        
        // overlap[i][j] = maximum overlap when words[i] is followed by words[j]
        int[][] overlap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                overlap[i][j] = getOverlap(words[i], words[j]);
            }
        }
        
        // dp[mask][i] = length of shortest superstring that visits exactly the set 'mask'
        // and ends with words[i]
        int[][] dp = new int[1 << n][n];
        int[][] parent = new int[1 << n][n]; // for path reconstruction
        
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE / 2);
        for (int[] row : parent) Arrays.fill(row, -1);
        
        // Base case: single word
        for (int i = 0; i < n; i++) {
            dp[1 << i][i] = words[i].length();
        }
        
        // Fill DP
        for (int mask = 0; mask < (1 << n); mask++) {
            for (int last = 0; last < n; last++) {
                if ((mask & (1 << last)) == 0) continue;
                
                // Try to append a new word
                for (int next = 0; next < n; next++) {
                    if ((mask & (1 << next)) != 0) continue;
                    
                    int newMask = mask | (1 << next);
                    int newLen = dp[mask][last] + words[next].length() - overlap[last][next];
                    
                    if (newLen < dp[newMask][next]) {
                        dp[newMask][next] = newLen;
                        parent[newMask][next] = last;
                    }
                }
            }
        }
        
        // Find the best ending word
        int fullMask = (1 << n) - 1;
        int minLen = Integer.MAX_VALUE;
        int lastWord = -1;
        for (int i = 0; i < n; i++) {
            if (dp[fullMask][i] < minLen) {
                minLen = dp[fullMask][i];
                lastWord = i;
            }
        }
        
        // Reconstruct the path
        List<Integer> path = new ArrayList<>();
        int mask = fullMask;
        int curr = lastWord;
        while (curr != -1) {
            path.add(curr);
            int prev = parent[mask][curr];
            mask ^= (1 << curr);
            curr = prev;
        }
        Collections.reverse(path);
        
        // Build the final string
        StringBuilder sb = new StringBuilder(words[path.get(0)]);
        for (int i = 1; i < path.size(); i++) {
            int prev = path.get(i - 1);
            int next = path.get(i);
            int over = overlap[prev][next];
            sb.append(words[next].substring(over));
        }
        
        return sb.toString();
    }
    
    // Maximum overlap of suffix of a and prefix of b
    private int getOverlap(String a, String b) {
        int max = Math.min(a.length(), b.length());
        for (int len = max; len > 0; len--) {
            if (a.endsWith(b.substring(0, len))) {
                return len;
            }
        }
        return 0;
    }
}