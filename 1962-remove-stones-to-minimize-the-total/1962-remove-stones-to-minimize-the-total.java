class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int p : piles) pq.offer(p);
        while (k-- > 0) {
            int x = pq.poll();
            x -= x / 2;
            pq.offer(x);
        }
        int sum = 0;
        while (!pq.isEmpty()) sum += pq.poll();
        return sum;
    }
}