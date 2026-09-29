class Solution {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{tasks[i][0], tasks[i][1], i};
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);
        int[] res = new int[n];
        int idx = 0, time = 0, i = 0;
        while (idx < n) {
            while (i < n && arr[i][0] <= time) {
                pq.offer(arr[i]);
                i++;
            }
            if (pq.isEmpty()) {
                time = arr[i][0];
                continue;
            }
            int[] cur = pq.poll();
            res[idx++] = cur[2];
            time += cur[1];
        }
        return res;
    }
}