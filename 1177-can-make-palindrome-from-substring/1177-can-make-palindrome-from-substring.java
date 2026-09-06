class Solution {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        int[][] prefix = new int[n + 1][26];
        
        // Build prefix frequency array
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                prefix[i + 1][j] = prefix[i][j];
            }
            prefix[i + 1][s.charAt(i) - 'a']++;
        }
        
        List<Boolean> answer = new ArrayList<>();
        
        for (int[] q : queries) {
            int left = q[0];
            int right = q[1];
            int k = q[2];
            
            int oddCount = 0;
            for (int c = 0; c < 26; c++) {
                int freq = prefix[right + 1][c] - prefix[left][c];
                if (freq % 2 == 1) {
                    oddCount++;
                }
            }
            
            // Each replacement can fix 2 odd counts
            answer.add(oddCount / 2 <= k);
        }
        
        return answer;
    }
}