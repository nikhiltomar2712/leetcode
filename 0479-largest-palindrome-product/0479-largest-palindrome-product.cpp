class Solution {
public:
    int largestPalindrome(int n) {
        if (n == 1) return 9;
        
        // Calculate the upper and lower bounds for n-digit numbers
        long long upper = pow(10, n) - 1;
        long long lower = pow(10, n - 1);
        
        // Try all possible palindromes from largest to smallest
        for (long long i = upper; i >= lower; i--) {
            // Create palindrome by mirroring the first half
            string s = to_string(i);
            string palindrome = s + string(s.rbegin(), s.rend());
            long long pal = stoll(palindrome);
            
            // Check if this palindrome can be expressed as product of two n-digit numbers
            for (long long j = upper; j * j >= pal; j--) {
                if (pal % j == 0) {
                    long long factor = pal / j;
                    if (factor >= lower && factor <= upper) {
                        return pal % 1337;
                    }
                }
            }
        }
        
        return -1;
    }
};