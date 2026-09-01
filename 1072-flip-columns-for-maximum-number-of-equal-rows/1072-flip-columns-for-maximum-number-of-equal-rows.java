class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> count = new HashMap<>();
        int max = 0;
        int n = matrix[0].length;

        for (int[] row : matrix) {
            // Normalize the row so that it starts with 0
            // by XORing every element with the first element
            char[] pattern = new char[n];
            for (int i = 0; i < n; i++) {
                pattern[i] = (char) (row[0] ^ row[i]);
            }
            String key = String.valueOf(pattern);
            count.put(key, count.getOrDefault(key, 0) + 1);
            max = Math.max(max, count.get(key));
        }

        return max;
    }
}