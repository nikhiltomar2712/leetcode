class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Max-heap ordered by distance from origin (largest distance on top)
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(dist(b), dist(a))
        );

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();          // remove the farthest point
            }
        }

        // Collect the k closest points
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        return result;
    }

    // Squared Euclidean distance (no need for sqrt)
    private int dist(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }
}