import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> reserved = new HashMap<>();
        
        // Group reserved seats by row
        for (int[] seat : reservedSeats) {
            reserved.computeIfAbsent(seat[0], k -> new HashSet<>()).add(seat[1]);
        }
        
        int ans = 0;
        
        // Process only the rows that have reservations
        for (Set<Integer> seats : reserved.values()) {
            boolean left = true;   // seats 2-5
            boolean middle = true; // seats 4-7
            boolean right = true;  // seats 6-9
            
            for (int s : new int[]{2, 3, 4, 5}) {
                if (seats.contains(s)) left = false;
            }
            for (int s : new int[]{4, 5, 6, 7}) {
                if (seats.contains(s)) middle = false;
            }
            for (int s : new int[]{6, 7, 8, 9}) {
                if (seats.contains(s)) right = false;
            }
            
            if (left && right) {
                ans += 2;          // both sides free → 2 families
            } else if (left || right || middle) {
                ans += 1;          // only one possible block
            }
            // else 0 families in this row
        }
        
        // Empty rows (no reservations) can always take 2 families
        ans += 2 * (n - reserved.size());
        
        return ans;
    }
}