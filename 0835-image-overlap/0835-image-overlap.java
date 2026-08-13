import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        // Lists to store coordinates of 1s in both images
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();
        
        // Collect all 1 positions
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) ones1.add(new int[]{i, j});
                if (img2[i][j] == 1) ones2.add(new int[]{i, j});
            }
        }
        
        // Edge case: if either image has no 1s, overlap is 0
        if (ones1.isEmpty() || ones2.isEmpty()) return 0;
        
        // Map to count frequency of each translation vector
        Map<String, Integer> map = new HashMap<>();
        int maxOverlap = 0;
        
        // For every pair of 1s (one from img1, one from img2),
        // compute the translation needed to align them
        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {
                // Translation to move p1 onto p2: (p2.row - p1.row, p2.col - p1.col)
                String key = (p2[0] - p1[0]) + "," + (p2[1] - p1[1]);
                int count = map.getOrDefault(key, 0) + 1;
                map.put(key, count);
                maxOverlap = Math.max(maxOverlap, count);
            }
        }
        
        return maxOverlap;
    }
}