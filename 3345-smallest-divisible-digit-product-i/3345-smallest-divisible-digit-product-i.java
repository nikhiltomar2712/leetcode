class Solution {
    public int smallestNumber(int n, int t) {
        for (int num = n; ; num++) {
            if (digitProductDivisible(num, t)) {
                return num;
            }
        }
    }

    private boolean digitProductDivisible(int num, int t) {
        int product = 1;
        while (num > 0) {
            int digit = num % 10;
            product *= digit;
            // If product becomes 0, it's divisible by any t (since 0 % t == 0)
            if (product == 0) {
                return true;
            }
            num /= 10;
        }
        return product % t == 0;
    }
}