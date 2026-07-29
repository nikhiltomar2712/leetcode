class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        int n = positions.length;
        int[] heights = new int[n];          // final top height of each square
        List<Integer> ans = new ArrayList<>();
        int maxH = 0;

        for (int i = 0; i < n; i++) {
            int left = positions[i][0];
            int size = positions[i][1];
            int right = left + size;

            int base = 0;                    // highest square we land on
            for (int j = 0; j < i; j++) {
                int prevLeft = positions[j][0];
                int prevRight = prevLeft + positions[j][1];

                // check overlap
                if (left < prevRight && prevLeft < right) {
                    base = Math.max(base, heights[j]);
                }
            }

            heights[i] = base + size;
            maxH = Math.max(maxH, heights[i]);
            ans.add(maxH);
        }
        return ans;
    }
}