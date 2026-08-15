class Solution {
    public int mirrorReflection(int p, int q) {
        // Reduce by GCD to simplify the fractions
        int g = gcd(p, q);
        p /= g;
        q /= g;
        
        // Now look at the parity of the reduced p and q
        p %= 2;
        q %= 2;
        
        if (p == 1 && q == 1) return 1;  // hits receptor 1
        if (p == 1) return 0;            // hits receptor 0
        return 2;                       // hits receptor 2
    }
    
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}