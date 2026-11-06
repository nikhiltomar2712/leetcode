import java.util.HashSet;
import java.util.Set;

class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int MOD = 1_000_000_007;

        Set<Integer> hDists = getDistances(m, hFences);
        Set<Integer> vDists = getDistances(n, vFences);

        long maxSide = -1;
        for (int d : hDists) {
            if (vDists.contains(d)) {
                maxSide = Math.max(maxSide, d);
            }
        }

        if (maxSide == -1) return -1;
        return (int) ((maxSide * maxSide) % MOD);
    }

    private Set<Integer> getDistances(int limit, int[] fences) {
        int k = fences.length;
        int[] pts = new int[k + 2];
        pts[0] = 1;
        pts[k + 1] = limit;
        for (int i = 0; i < k; i++) pts[i + 1] = fences[i];

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                set.add(Math.abs(pts[j] - pts[i]));
            }
        }
        return set;
    }
}