class Solution {
    public int findCrossingTime(int n, int k, int[][] time) {
        PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> {
            int effA = time[a][0] + time[a][2];
            int effB = time[b][0] + time[b][2];
            if (effA != effB) return effB - effA;
            return b - a;
        });
        PriorityQueue<Integer> right = new PriorityQueue<>((a, b) -> {
            int effA = time[a][0] + time[a][2];
            int effB = time[b][0] + time[b][2];
            if (effA != effB) return effB - effA;
            return b - a;
        });
        PriorityQueue<int[]> leftWait = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> rightWait = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < k; i++) left.offer(i);
        int timeNow = 0;
        while (n > 0 || !right.isEmpty() || !rightWait.isEmpty()) {
            while (!rightWait.isEmpty() && rightWait.peek()[0] <= timeNow) {
                right.offer(rightWait.poll()[1]);
            }
            while (!leftWait.isEmpty() && leftWait.peek()[0] <= timeNow) {
                left.offer(leftWait.poll()[1]);
            }
            if (!right.isEmpty()) {
                int w = right.poll();
                timeNow += time[w][2];
                leftWait.offer(new int[]{timeNow + time[w][3], w});
            } else if (!left.isEmpty() && n > 0) {
                int w = left.poll();
                timeNow += time[w][0];
                n--;
                rightWait.offer(new int[]{timeNow + time[w][1], w});
            } else {
                int nextLeft = leftWait.isEmpty() ? Integer.MAX_VALUE : leftWait.peek()[0];
                int nextRight = rightWait.isEmpty() ? Integer.MAX_VALUE : rightWait.peek()[0];
                timeNow = Math.min(nextLeft, nextRight);
            }
        }
        return timeNow;
    }
}