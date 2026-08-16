class Solution {
    private static final int MOD = 1_000_000_007;

    public int nthMagicalNumber(int n, int a, int b) {
        long lcm = (long) a / gcd(a, b) * b;  // careful with overflow
        long left = 1;
        long right = (long) Math.min(a, b) * n;

        while (left < right) {
            long mid = left + (right - left) / 2;
            long count = mid / a + mid / b - mid / lcm;

            if (count >= n) {
                right = mid;          // try smaller
            } else {
                left = mid + 1;       // need larger
            }
        }

        return (int) (left % MOD);
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}