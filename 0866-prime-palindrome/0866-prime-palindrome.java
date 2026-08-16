class Solution {
    public int primePalindrome(int n) {
        while (true) {
            if (isPalindrome(n) && isPrime(n)) {
                return n;
            }
            // Skip all 8-digit numbers (they are all divisible by 11)
            if (n > 10_000_000 && n < 100_000_000) {
                n = 100_000_000;
            } else {
                n++;
            }
        }
    }

    private boolean isPrime(int x) {
        if (x < 2) return false;
        if (x == 2) return true;
        if (x % 2 == 0) return false;
        for (int i = 3; i * i <= x; i += 2) {
            if (x % i == 0) return false;
        }
        return true;
    }

    private boolean isPalindrome(int x) {
        return x == reverse(x);
    }

    private int reverse(int x) {
        int res = 0;
        while (x > 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }
}