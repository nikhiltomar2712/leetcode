class Solution {
    public int maxRepOpt1(String text) {
        int n = text.length();
        int[] totalCount = new int[26];
        for (char c : text.toCharArray()) {
            totalCount[c - 'a']++;
        }
        
        // Group into runs: list of [char, length]
        List<int[]> groups = new ArrayList<>();
        int i = 0;
        while (i < n) {
            char c = text.charAt(i);
            int j = i;
            while (j < n && text.charAt(j) == c) {
                j++;
            }
            groups.add(new int[]{c - 'a', j - i});
            i = j;
        }
        
        int maxLen = 0;
        
        // Case 1: single group, can extend by 1 if total > group length
        for (int[] g : groups) {
            int ch = g[0], len = g[1];
            maxLen = Math.max(maxLen, Math.min(len + 1, totalCount[ch]));
        }
        
        // Case 2: two groups of same char separated by a single different char
        for (int idx = 0; idx < groups.size() - 2; idx++) {
            int[] g1 = groups.get(idx);
            int[] mid = groups.get(idx + 1);
            int[] g2 = groups.get(idx + 2);
            
            if (g1[0] == g2[0] && mid[1] == 1) {
                int ch = g1[0];
                int combined = g1[1] + g2[1];
                // Can we extend further? Only if total > combined (we used the swap to bridge)
                maxLen = Math.max(maxLen, Math.min(combined + 1, totalCount[ch]));
            }
        }
        
        return maxLen;
    }
}