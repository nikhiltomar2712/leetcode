class Solution {
    public int nthUglyNumber(int n, int a, int b, int c) {
        long ab = lcm(a, b);
        long ac = lcm(a, c);
        long bc = lcm(b, c);
        long abc = lcm(a, bc);   // lcm(a, b, c)
        
        long left = 1;
        long right = 2_000_000_000L;  // maximum possible answer
        
        while (left < right) {
            long mid = left + (right - left) / 2;
            
            // Count how many ugly numbers ≤ mid using inclusion-exclusion
            long count = mid / a + mid / b + mid / c
                       - mid / ab - mid / ac - mid / bc
                       + mid / abc;
            
            if (count >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return (int) left;
    }
    
    // Least Common Multiple
    private long lcm(long x, long y) {
        return x / gcd(x, y) * y;
    }
    
    // Greatest Common Divisor
    private long gcd(long x, long y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}