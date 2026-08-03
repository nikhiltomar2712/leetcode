class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        // Min-heap ordered by fraction value: a/b
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0] * b[1], a[1] * b[0])
        );

        // Initially push 1 / arr[j] for every j > 0
        for (int j = 1; j < n; j++) {
            pq.offer(new int[]{arr[0], arr[j], 0, j}); // {num, den, i, j}
        }

        // Pop the smallest fraction k-1 times
        for (int t = 0; t < k - 1; t++) {
            int[] cur = pq.poll();
            int i = cur[2], j = cur[3];
            if (i + 1 < j) {
                pq.offer(new int[]{arr[i + 1], arr[j], i + 1, j});
            }
        }

        int[] ans = pq.poll();
        return new int[]{ans[0], ans[1]};
    }
}