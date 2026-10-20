class Solution {
    public long maxPower(int[] stations, int r, long k) {
        int n = stations.length;
        long[] base = new long[n];
        long[] diff = new long[n + 1];
        for (int i = 0; i < n; i++) {
            int left = Math.max(0, i - r);
            int right = Math.min(n, i + r + 1);
            diff[left] += stations[i];
            diff[right] -= stations[i];
        }
        long cur = 0;
        for (int i = 0; i < n; i++) {
            cur += diff[i];
            base[i] = cur;
        }
        long low = 0, high = (long) 1e18;
        while (low < high) {
            long mid = low + (high - low + 1) / 2;
            if (canAchieve(base, r, k, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean canAchieve(long[] base, int r, long k, long target) {
        int n = base.length;
        long[] add = new long[n + 1];
        long extra = 0;
        long used = 0;
        for (int i = 0; i < n; i++) {
            extra += add[i];
            long curPower = base[i] + extra;
            if (curPower < target) {
                long need = target - curPower;
                used += need;
                if (used > k) return false;
                extra += need;
                int end = Math.min(n, i + 2 * r + 1);
                add[end] -= need;
            }
        }
        return true;
    }
}