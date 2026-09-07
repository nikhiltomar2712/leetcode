import java.util.*;

class Solution {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        // Use a set for O(1) queen position lookups
        Set<String> queenSet = new HashSet<>();
        for (int[] q : queens) {
            queenSet.add(q[0] + "," + q[1]);
        }
        
        List<List<Integer>> result = new ArrayList<>();
        
        // 8 directions: up, down, left, right, and 4 diagonals
        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}, // vertical and horizontal
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // diagonals
        };
        
        // Check each direction from the king
        for (int[] dir : directions) {
            int x = king[0] + dir[0];
            int y = king[1] + dir[1];
            
            // Move step by step until we hit the board edge
            while (x >= 0 && x < 8 && y >= 0 && y < 8) {
                if (queenSet.contains(x + "," + y)) {
                    // Found the nearest queen in this direction
                    result.add(Arrays.asList(x, y));
                    break; // Stop searching this direction
                }
                x += dir[0];
                y += dir[1];
            }
        }
        
        return result;
    }
}