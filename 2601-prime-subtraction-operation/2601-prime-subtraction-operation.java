class Solution {
    public boolean primeSubOperation(int[] nums) {
        int max = 1000;
        boolean[] isPrime = new boolean[max + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= max; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= max; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= max; i++) {
            if (isPrime[i]) primes.add(i);
        }
        int n = nums.length;
        int prev = 0;
        for (int i = 0; i < n; i++) {
            int best = -1;
            for (int p : primes) {
                if (nums[i] - p > prev) {
                    best = nums[i] - p;
                } else {
                    break;
                }
            }
            if (best == -1) {
                if (nums[i] <= prev) return false;
                best = nums[i];
            }
            prev = best;
        }
        return true;
    }
}