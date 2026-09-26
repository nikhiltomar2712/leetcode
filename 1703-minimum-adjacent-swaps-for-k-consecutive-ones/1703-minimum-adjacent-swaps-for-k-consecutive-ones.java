class Solution {
    public int minMoves(int[] nums, int k) {
        List<Integer> pos = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) pos.add(i);
        }

        int n = pos.size();
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + pos.get(i);
        }

        long ans = Long.MAX_VALUE;
        for (int i = 0; i + k <= n; i++) {
            int mid = i + k / 2;
            long median = pos.get(mid);
            long leftCount = mid - i;
            long rightCount = i + k - 1 - mid;

            long leftSum = prefix[mid] - prefix[i];
            long rightSum = prefix[i + k] - prefix[mid + 1];

            long cost = median * leftCount - leftSum
                      + rightSum - median * rightCount;

            long offset = leftCount * (leftCount + 1) / 2
                        + rightCount * (rightCount + 1) / 2;

            ans = Math.min(ans, cost - offset);
        }

        return (int) ans;
    }
}