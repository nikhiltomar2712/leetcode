class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Free servers: [weight, index], smaller weight first; if equal, smaller index first
        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // Busy servers: [freeTime, weight, index], smaller freeTime first; if equal, smaller weight first, then smaller index first
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });

        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        long time = 0; // Current time, use long to prevent overflow

        for (int i = 0; i < m; i++) {
            // Tasks can only be processed starting from the i-th second
            time = Math.max(time, i);

            // Move all servers that have completed their tasks back to the free queue
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] server = busy.poll();
                free.offer(new int[]{server[1], server[2]});
            }

            // If no free server, wait until the earliest server becomes available
            if (free.isEmpty()) {
                time = busy.peek()[0];
                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    int[] server = busy.poll();
                    free.offer(new int[]{server[1], server[2]});
                }
            }

            // Assign the optimal free server
            int[] server = free.poll();
            ans[i] = server[1];
            busy.offer(new int[]{(int)(time + tasks[i]), server[0], server[1]});
        }

        return ans;
    }
}