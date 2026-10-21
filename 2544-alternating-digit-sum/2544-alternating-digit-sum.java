class Solution {
    public int alternateDigitSum(int n) {
        // Count number of digits to decide the starting sign
        int digits = (int) Math.log10(n) + 1;
        int sign = (digits % 2 == 1) ? 1 : -1;  // leftmost digit should end up positive
        
        int sum = 0;
        while (n > 0) {
            sum += sign * (n % 10);
            sign = -sign;
            n /= 10;
        }
        return sum;
    }
}