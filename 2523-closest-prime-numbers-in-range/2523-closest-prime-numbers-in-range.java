class Solution {
    public int[] closestPrimes(int left, int right) {
        boolean[] isPrime = sieve(right);
        int prev = -1;
        int[] res = {-1, -1};
        int minDiff = Integer.MAX_VALUE;
        for (int i = Math.max(2, left); i <= right; i++) {
            if (isPrime[i]) {
                if (prev != -1 && i - prev < minDiff) {
                    minDiff = i - prev;
                    res[0] = prev;
                    res[1] = i;
                }
                prev = i;
            }
        }
        return res;
    }

    private boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        if (n >= 0) isPrime[0] = false;
        if (n >= 1) isPrime[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }
}