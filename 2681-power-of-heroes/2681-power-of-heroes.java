class Solution {
    public int sumOfPower(int[] nums) {
        int mod = 1000000007;
        Arrays.sort(nums);
        long res = 0;
        long prefix = 0;
        for (int x : nums) {
            long v = (long) x * x % mod;
            res = (res + v * x % mod + v * prefix % mod) % mod;
            prefix = (prefix * 2 + x) % mod;
        }
        return (int) res;
    }
}