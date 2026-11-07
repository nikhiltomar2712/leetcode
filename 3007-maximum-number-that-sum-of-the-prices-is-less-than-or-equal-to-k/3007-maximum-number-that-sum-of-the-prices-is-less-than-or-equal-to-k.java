class Solution {
    private long k;

    public long findMaximumNumber(long k, int x) {
        this.k = k;
        long low = 1, high = (long) 1e15;
        while (low < high) {
            long mid = high - (high - low) / 2;
            if (calculatePrice(mid, x) <= k) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private long calculatePrice(long num, int x) {
        long sum = 0;
        for (int bit = x - 1; bit < 63; bit += x) {
            long period = 1L << (bit + 1);
            long half = 1L << bit;
            long fullCycles = (num + 1) / period;
            long remainder = (num + 1) % period;

            sum += fullCycles * half;
            if (remainder > half) {
                sum += remainder - half;
            }
            if (sum > k) return sum; // k is now a field
        }
        return sum;
    }
}