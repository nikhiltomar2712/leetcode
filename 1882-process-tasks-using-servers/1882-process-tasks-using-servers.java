class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> a[2] == b[2] ? (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]) : a[2] - b[2]);
        for (int i = 0; i < servers.length; i++) {
            free.offer(new int[]{servers[i], i, 0});
        }
        int[] res = new int[tasks.length];
        int time = 0;
        for (int i = 0; i < tasks.length; i++) {
            time = Math.max(time, i);
            while (!busy.isEmpty() && busy.peek()[2] <= time) {
                int[] s = busy.poll();
                free.offer(new int[]{s[0], s[1], 0});
            }
            if (free.isEmpty()) {
                time = busy.peek()[2];
                while (!busy.isEmpty() && busy.peek()[2] <= time) {
                    int[] s = busy.poll();
                    free.offer(new int[]{s[0], s[1], 0});
                }
            }
            int[] s = free.poll();
            res[i] = s[1];
            busy.offer(new int[]{s[0], s[1], time + tasks[i]});
        }
        return res;
    }
}