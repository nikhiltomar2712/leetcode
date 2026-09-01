class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // max-heap
        for (int stone : stones) {
            pq.offer(stone);
        }
        
        while (pq.size() > 1) {
            int y = pq.poll(); // heaviest
            int x = pq.poll(); // second heaviest
            if (x != y) {
                pq.offer(y - x);
            }
        }
        
        return pq.isEmpty() ? 0 : pq.poll();
    }
}