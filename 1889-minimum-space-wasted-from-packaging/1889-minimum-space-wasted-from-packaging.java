class Solution {
    public int minWastedSpace(int[] packages, int[][] boxes) {
        int mod = 1000000007;
        Arrays.sort(packages);
        long[] prefix = new long[packages.length + 1];
        for (int i = 0; i < packages.length; i++) {
            prefix[i + 1] = prefix[i] + packages[i];
        }
        long res = Long.MAX_VALUE;
        for (int[] box : boxes) {
            Arrays.sort(box);
            if (box[box.length - 1] < packages[packages.length - 1]) continue;
            long waste = 0;
            int prev = 0;
            for (int b : box) {
                int idx = upperBound(packages, b);
                waste += (long) b * (idx - prev) - (prefix[idx] - prefix[prev]);
                prev = idx;
                if (prev == packages.length) break;
            }
            res = Math.min(res, waste);
        }
        return res == Long.MAX_VALUE ? -1 : (int) (res % mod);
    }

    private int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}