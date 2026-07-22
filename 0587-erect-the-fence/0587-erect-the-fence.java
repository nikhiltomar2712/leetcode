import java.util.*;

class Solution {
    public int[][] outerTrees(int[][] trees) {
        int n = trees.length;
        if (n <= 1) return trees;
        
        Arrays.sort(trees, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        
        List<int[]> hull = new ArrayList<>();
        
        // lower hull
        for (int[] p : trees) {
            while (hull.size() >= 2 && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) < 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(p);
        }
        
        int lowerSize = hull.size();
        
        // upper hull
        for (int i = n - 2; i >= 0; i--) {
            int[] p = trees[i];
            while (hull.size() > lowerSize && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) < 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(p);
        }
        
        if (hull.size() > 1) {
            hull.remove(hull.size() - 1); // remove duplicate
        }
        
        // remove duplicates using set
        Set<int[]> set = new HashSet<>(hull);
        return set.toArray(new int[0][]);
    }
    
    private int cross(int[] o, int[] a, int[] b) {
        return (a[0] - o[0]) * (b[1] - o[1]) - (a[1] - o[1]) * (b[0] - o[0]);
    }
}