class Solution {
    public boolean canConstruct(String s, int k) {
        int n = s.length();
        
        // Can't form more palindromes than characters
        if (n < k) return false;
        
        // Count character frequencies
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        // Count characters with odd frequency
        int oddCount = 0;
        for (int f : freq) {
            if (f % 2 != 0) oddCount++;
        }
        
        // Each palindrome can absorb at most one odd-frequency character
        return oddCount <= k;
    }
}