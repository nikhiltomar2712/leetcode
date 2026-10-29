class Solution {
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int[] count = new int[101];
        for (int i = 0; i < k; i++) {
            count[nums[i] + 50]++;
        }
        for (int i = 0; i <= n - k; i++) {
            if (i > 0) {
                count[nums[i - 1] + 50]--;
                count[nums[i + k - 1] + 50]++;
            }
            int seen = 0;
            res[i] = 0;
            for (int v = -50; v < 0; v++) {
                seen += count[v + 50];
                if (seen >= x) {
                    res[i] = v;
                    break;
                }
            }
        }
        return res;
    }
}