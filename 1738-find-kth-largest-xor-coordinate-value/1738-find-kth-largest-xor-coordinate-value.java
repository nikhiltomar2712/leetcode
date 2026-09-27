class Solution {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = matrix[i - 1][j - 1]
                          ^ pre[i - 1][j]
                          ^ pre[i][j - 1]
                          ^ pre[i - 1][j - 1];

                minHeap.offer(pre[i][j]);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
        }

        return minHeap.peek();
    }
}