class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        
        // Check if a palindromic permutation is possible
        int oddCount = 0;
        for (int c : cnt) {
            if (c % 2 != 0) oddCount++;
        }
        if (oddCount > 1) return "";
        
        // Find the middle character for odd length (the one with odd count)
        int mid = -1;
        if (n % 2 == 1) {
            for (int i = 0; i < 26; i++) {
                if (cnt[i] % 2 == 1) {
                    mid = i;
                    cnt[i]--;  // Reserve it for the middle
                    break;
                }
            }
        }
        
        // Try to match the first half of target as much as possible
        StringBuilder half = new StringBuilder();
        int halfLen = n / 2;
        boolean matched = true;
        
        for (int i = 0; i < halfLen; i++) {
            int c = target.charAt(i) - 'a';
            cnt[c] -= 2;
            half.append((char) ('a' + c));
            if (cnt[c] < 0) {
                matched = false;
                break;
            }
        }
        
        if (matched) {
            // Successfully matched the entire first half of target
            StringBuilder res = new StringBuilder(half);
            if (n % 2 == 1) {
                res.append((char) ('a' + mid));
            }
            // Append the reverse of the first half
            for (int i = half.length() - 1; i >= 0; i--) {
                res.append(half.charAt(i));
            }
            String candidate = res.toString();
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
            // Need a larger one; backtrack the middle if present
            if (n % 2 == 1) {
                half.setLength(half.length()); // already without mid
            }
        }
        
        // Backtrack to find the next greater first half
        while (half.length() > 0) {
            int c = half.charAt(half.length() - 1) - 'a';
            half.setLength(half.length() - 1);
            cnt[c] += 2;  // Restore the two characters
            
            // Try the next greater character
            boolean found = false;
            for (int next = c + 1; next < 26; next++) {
                if (cnt[next] >= 2) {
                    cnt[next] -= 2;
                    half.append((char) ('a' + next));
                    found = true;
                    break;
                }
            }
            
            if (found) {
                // Fill the remaining positions with the smallest possible characters
                for (int j = 0; j < 26; j++) {
                    while (cnt[j] >= 2) {
                        cnt[j] -= 2;
                        half.append((char) ('a' + j));
                    }
                }
                
                // Build the full palindrome
                StringBuilder res = new StringBuilder(half);
                if (n % 2 == 1) {
                    res.append((char) ('a' + mid));
                }
                for (int i = half.length() - 1; i >= 0; i--) {
                    res.append(half.charAt(i));
                }
                return res.toString();
            }
        }
        
        return "";
    }
}