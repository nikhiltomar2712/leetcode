class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int n = queries.length;
        int[][] q = new int[n][2];
        for (int i = 0; i < n; i++) {
            q[i] = new int[]{queries[i], i};
        }
        Arrays.sort(q, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - a[0]) - (b[1] - b[0]));
        int[] res = new int[n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int query = q[i][0], qi = q[i][1];
            while (idx < intervals.length && intervals[idx][0] <= query) {
                pq.offer(intervals[idx]);
                idx++;
            }
            while (!pq.isEmpty() && pq.peek()[1] < query) {
                pq.poll();
            }
            res[qi] = pq.isEmpty() ? -1 : pq.peek()[1] - pq.peek()[0] + 1;
        }
        return res;
    }
}