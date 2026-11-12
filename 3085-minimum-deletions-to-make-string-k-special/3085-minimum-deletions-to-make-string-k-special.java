class Solution {
    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        
        int ans = word.length();
        
        for (int base : freq) {
            if (base == 0) continue;
            
            int deletions = 0;
            for (int f : freq) {
                if (f == 0) continue;
                
                if (f < base) {
                    deletions += f;
                } else if (f > base + k) {
                    deletions += f - (base + k);
                }
            }
            ans = Math.min(ans, deletions);
        }
        
        return ans;
    }
}