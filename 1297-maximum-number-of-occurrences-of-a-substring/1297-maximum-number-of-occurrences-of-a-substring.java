class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> count = new HashMap<>();
        int max = 0;
        
        for (int i = 0; i <= s.length() - minSize; i++) {
            String sub = s.substring(i, i + minSize);
            if (uniqueCount(sub) <= maxLetters) {
                int freq = count.merge(sub, 1, Integer::sum);
                max = Math.max(max, freq);
            }
        }
        return max;
    }
    
    private int uniqueCount(String str) {
        boolean[] seen = new boolean[26];
        int cnt = 0;
        for (char c : str.toCharArray()) {
            if (!seen[c - 'a']) {
                seen[c - 'a'] = true;
                cnt++;
            }
        }
        return cnt;
    }
}