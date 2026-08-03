class Solution {
    public int preimageSizeFZF(int k) {
        // Find the smallest x such that f(x) >= k
        long left = 0;
        long right = 5L * k;          // safe upper bound

        while (left < right) {
            long mid = (left + right) / 2;
            if (trailingZeroes(mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // If f(left) == k, there are 5 solutions; otherwise 0
        return trailingZeroes(left) == k ? 5 : 0;
    }

    // Classic function: number of trailing zeroes in n!
    private long trailingZeroes(long n) {
        long count = 0;
        while (n > 0) {
            n /= 5;
            count += n;
        }
        return count;
    }
}