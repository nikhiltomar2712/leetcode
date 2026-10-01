class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{times[i][0], times[i][1], i};
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> free = new PriorityQueue<>();
        for (int i = 0; i < n; i++) free.offer(i);
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] t : arr) {
            int arrival = t[0], leaving = t[1], idx = t[2];
            while (!busy.isEmpty() && busy.peek()[0] <= arrival) {
                free.offer(busy.poll()[1]);
            }
            int chair = free.poll();
            if (idx == targetFriend) return chair;
            busy.offer(new int[]{leaving, chair});
        }
        return -1;
    }
}