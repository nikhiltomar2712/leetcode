import java.util.*;

class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        // Map: first element of piece → the piece
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] piece : pieces) {
            map.put(piece[0], piece);
        }

        int i = 0;
        while (i < arr.length) {
            // Look up the piece starting with arr[i]
            int[] piece = map.get(arr[i]);
            if (piece == null) return false;

            // Verify the piece matches arr at position i
            for (int val : piece) {
                if (i >= arr.length || arr[i] != val) return false;
                i++;
            }
        }

        return true;
    }
}