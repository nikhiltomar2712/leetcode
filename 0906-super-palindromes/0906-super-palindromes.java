class Solution {
    public int superpalindromesInRange(String left, String right) {
        long L = Long.parseLong(left);
        long R = Long.parseLong(right);
        int count = 0;
        
        // Generate all possible palindromic roots whose square can be <= 10^18
        // Root itself can be at most 10^9 (since sqrt(10^18) = 10^9)
        
        // 1. Odd-length palindromes
        // We generate the first half and mirror it
        for (int i = 1; i < 100000; i++) {
            String s = Integer.toString(i);
            StringBuilder sb = new StringBuilder(s);
            // Mirror without the last digit for odd length
            for (int j = s.length() - 2; j >= 0; j--) {
                sb.append(s.charAt(j));
            }
            long p = Long.parseLong(sb.toString());
            long square = p * p;
            if (square > R) break;
            if (square >= L && isPalindrome(square)) {
                count++;
            }
        }
        
        // 2. Even-length palindromes
        for (int i = 1; i < 100000; i++) {
            String s = Integer.toString(i);
            StringBuilder sb = new StringBuilder(s);
            // Full mirror for even length
            for (int j = s.length() - 1; j >= 0; j--) {
                sb.append(s.charAt(j));
            }
            long p = Long.parseLong(sb.toString());
            long square = p * p;
            if (square > R) break;
            if (square >= L && isPalindrome(square)) {
                count++;
            }
        }
        
        return count;
    }
    
    private boolean isPalindrome(long x) {
        String s = Long.toString(x);
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}