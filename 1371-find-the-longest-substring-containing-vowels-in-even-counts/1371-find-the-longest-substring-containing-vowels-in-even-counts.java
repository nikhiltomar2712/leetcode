class Solution {
    public int findTheLongestSubstring(String s) {
        // Map each vowel to a bit position
        // a → 0, e → 1, i → 2, o → 3, u → 4
        int[] first = new int[32]; // 2^5 = 32 possible masks
        Arrays.fill(first, -2);    // -2 means not seen
        first[0] = -1;             // empty prefix has mask 0 at index -1
        
        int mask = 0;
        int maxLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // Flip the corresponding bit if it's a vowel
            switch (c) {
                case 'a': mask ^= 1 << 0; break;
                case 'e': mask ^= 1 << 1; break;
                case 'i': mask ^= 1 << 2; break;
                case 'o': mask ^= 1 << 3; break;
                case 'u': mask ^= 1 << 4; break;
            }
            
            if (first[mask] == -2) {
                // First time seeing this mask
                first[mask] = i;
            } else {
                // Same mask seen before → valid substring
                maxLen = Math.max(maxLen, i - first[mask]);
            }
        }
        
        return maxLen;
    }
}