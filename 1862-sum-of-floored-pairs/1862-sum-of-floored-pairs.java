class Solution {
    public int sumOfFlooredPairs(int[] nums) {
        int mod = 1000000007;
        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        int[] count = new int[max + 1];
        for (int num : nums) count[num]++;
        int[] prefix = new int[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + count[i];
        }
        long res = 0;
        for (int i = 1; i <= max; i++) {
            if (count[i] == 0) continue;
            for (int j = i; j <= max; j += i) {
                int upper = Math.min(max, j + i - 1);
                long cnt = prefix[upper] - prefix[j - 1];
                res = (res + cnt * (j / i) % mod * count[i]) % mod;
            }
        }
        return (int) res;
    }
}