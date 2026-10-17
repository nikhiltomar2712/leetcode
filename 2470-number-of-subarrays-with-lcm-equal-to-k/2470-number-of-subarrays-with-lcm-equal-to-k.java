class Solution {
    public int subarrayLCM(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            long lcm = 1;
            for (int j = i; j < n; j++) {
                lcm = lcm(lcm, nums[j]);
                if (lcm > k) break;
                if (lcm == k) count++;
            }
        }
        return count;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}