class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> {
            int sa = digitSum(nums[a]), sb = digitSum(nums[b]);
            if (sa != sb) return sa - sb;
            return Integer.compare(nums[a], nums[b]);
        });
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = idx[i];
        }
        boolean[] visited = new boolean[n];
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            int cycleLen = 0;
            int cur = i;
            while (!visited[cur]) {
                visited[cur] = true;
                cur = arr[cur];
                cycleLen++;
            }
            if (cycleLen > 1) swaps += cycleLen - 1;
        }
        return swaps;
    }

    private int digitSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }
}