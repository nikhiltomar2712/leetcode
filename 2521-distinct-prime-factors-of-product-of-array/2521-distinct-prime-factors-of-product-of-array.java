class Solution {
    public int distinctPrimeFactors(int[] nums) {
        Set<Integer> primes = new HashSet<>();
        for (int num : nums) {
            int x = num;
            for (int i = 2; i * i <= x; i++) {
                if (x % i == 0) {
                    primes.add(i);
                    while (x % i == 0) x /= i;
                }
            }
            if (x > 1) primes.add(x);
        }
        return primes.size();
    }
}