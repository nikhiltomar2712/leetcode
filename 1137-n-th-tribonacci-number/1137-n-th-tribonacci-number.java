class Solution {
    public int tribonacci(int n) {
        // Base cases
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        
        // Iterative approach with three variables
        int a = 0; // T0
        int b = 1; // T1
        int c = 1; // T2
        int d = 0; // T3, T4, ...
        
        for (int i = 3; i <= n; i++) {
            d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        
        return d;
    }
}