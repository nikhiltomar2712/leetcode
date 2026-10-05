class Solution {
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int n = events.length;
        int[] maxValue = new int[n];
        maxValue[0] = events[0][2];
        for (int i = 1; i < n; i++) {
            maxValue[i] = Math.max(maxValue[i - 1], events[i][2]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int val = events[i][2];
            int lo = 0, hi = i - 1, best = -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (events[mid][1] < events[i][0]) {
                    best = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            if (best != -1) {
                val += maxValue[best];
            }
            res = Math.max(res, val);
        }
        return res;
    }
}