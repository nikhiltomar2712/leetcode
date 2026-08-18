class Solution {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String s = String.valueOf(n);
        int len = s.length();
        int d = digits.length;
        
        // Count of numbers with fewer digits than n
        int ans = 0;
        for (int i = 1; i < len; i++) {
            ans += Math.pow(d, i);
        }
        
        // Now count numbers with the same number of digits as n that are <= n
        boolean[] isDigit = new boolean[10];
        for (String dig : digits) {
            isDigit[dig.charAt(0) - '0'] = true;
        }
        
        for (int i = 0; i < len; i++) {
            int curr = s.charAt(i) - '0';
            
            // Count digits smaller than curr that we can place at this position
            for (int j = 0; j < curr; j++) {
                if (isDigit[j]) {
                    ans += Math.pow(d, len - i - 1);
                }
            }
            
            // If the current digit of n is not available, we cannot continue matching the prefix
            if (!isDigit[curr]) {
                return ans;
            }
        }
        
        // The number n itself can be formed
        return ans + 1;
    }
}