import java.util.PriorityQueue;

class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        // Min-heap of gaps covered by ladders
        PriorityQueue<Integer> ladderGaps = new PriorityQueue<>();

        for (int i = 0; i < heights.length - 1; i++) {
            int gap = heights[i + 1] - heights[i];
            if (gap <= 0) continue;  // free move

            ladderGaps.offer(gap);

            // If we've used more ladders than available, replace the smallest
            // ladder usage with bricks
            if (ladderGaps.size() > ladders) {
                bricks -= ladderGaps.poll();
            }

            // If bricks run out, we can't reach building i+1
            if (bricks < 0) {
                return i;
            }
        }

        return heights.length - 1;
    }
}