class Solution {
    public List<Long> minOperations(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        List<Long> res = new ArrayList<>();
        for (int q : queries) {
            int idx = lowerBound(nums, q);
            long leftCost = (long) q * idx - prefix[idx];
            long rightCost = (prefix[n] - prefix[idx]) - (long) q * (n - idx);
            res.add(leftCost + rightCost);
        }
        return res;
    }

    private int lowerBound(int[] nums, int target) {
        int lo = 0, hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}