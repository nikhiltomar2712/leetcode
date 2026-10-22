class Solution {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int g : gifts) pq.offer(g);
        while (k-- > 0) {
            int max = pq.poll();
            pq.offer((int) Math.sqrt(max));
        }
        long sum = 0;
        while (!pq.isEmpty()) sum += pq.poll();
        return sum;
    }
}