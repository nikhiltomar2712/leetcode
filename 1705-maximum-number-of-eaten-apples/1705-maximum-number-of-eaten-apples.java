class Solution {
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = apples.length;
        int eaten = 0;

        for (int i = 0; i < n || !pq.isEmpty(); i++) {
            if (i < n && apples[i] > 0) {
                pq.offer(new int[]{i + days[i], apples[i]});
            }
            while (!pq.isEmpty() && pq.peek()[0] <= i) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                int[] top = pq.poll();
                top[1]--;
                eaten++;
                if (top[1] > 0) {
                    pq.offer(top);
                }
            }
        }

        return eaten;
    }
}