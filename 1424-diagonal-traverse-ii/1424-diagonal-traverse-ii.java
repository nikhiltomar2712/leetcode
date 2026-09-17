import java.util.*;

class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int n = nums.size();

        // Map: diagonal index (i + j) -> values in bottom-to-top order
        Map<Integer, List<Integer>> diagonals = new HashMap<>();
        int maxKey = 0;
        int totalCount = 0;

        // Iterate rows bottom-to-top so each bucket fills bottom-to-top
        for (int i = n - 1; i >= 0; i--) {
            List<Integer> row = nums.get(i);
            for (int j = 0; j < row.size(); j++) {
                int key = i + j;
                diagonals.computeIfAbsent(key, x -> new ArrayList<>()).add(row.get(j));
                maxKey = Math.max(maxKey, key);
                totalCount++;
            }
        }

        int[] result = new int[totalCount];
        int idx = 0;

        // Append buckets in increasing diagonal order
        for (int d = 0; d <= maxKey; d++) {
            List<Integer> diag = diagonals.get(d);
            if (diag == null) continue;
            for (int val : diag) {
                result[idx++] = val;
            }
        }

        return result;
    }
}