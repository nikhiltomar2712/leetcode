class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] height = new int[n];
        int total = 0;

        for (int i = 0; i < m; i++) {
            // Update heights for this row
            for (int j = 0; j < n; j++) {
                height[j] = (mat[i][j] == 1) ? height[j] + 1 : 0;
            }

            // Monotonic stack: count submatrices ending at each column
            Deque<Integer> stack = new ArrayDeque<>();  // indices, increasing heights
            int[] sum = new int[n];  // sum[i] = submatrices ending at column i

            for (int j = 0; j < n; j++) {
                // Pop columns with height >= current height
                while (!stack.isEmpty() && height[stack.peek()] >= height[j]) {
                    stack.pop();
                }

                if (stack.isEmpty()) {
                    // All previous columns have height >= height[j]
                    sum[j] = height[j] * (j + 1);
                } else {
                    int prev = stack.peek();
                    sum[j] = sum[prev] + height[j] * (j - prev);
                }

                stack.push(j);
                total += sum[j];
            }
        }

        return total;
    }
}