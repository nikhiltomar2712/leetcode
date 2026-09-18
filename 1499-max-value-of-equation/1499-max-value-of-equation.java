class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        Deque<int[]> deque = new ArrayDeque<>();  // stores {x_i, y_i - x_i}
        int max = Integer.MIN_VALUE;

        for (int[] p : points) {
            int x = p[0], y = p[1];

            // Remove points too far away (outside window)
            while (!deque.isEmpty() && x - deque.peekFirst()[0] > k) {
                deque.pollFirst();
            }

            // If deque non-empty, front has max (y_i - x_i)
            if (!deque.isEmpty()) {
                max = Math.max(max, deque.peekFirst()[1] + y + x);
            }

            // Maintain decreasing order by (y_i - x_i)
            int curr = y - x;
            while (!deque.isEmpty() && deque.peekLast()[1] <= curr) {
                deque.pollLast();
            }
            deque.offerLast(new int[]{x, curr});
        }

        return max == Integer.MIN_VALUE ? -1 : max;
    }
}