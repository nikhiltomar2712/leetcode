import java.util.HashSet;
import java.util.Set;

class Solution {
    private int best = 0;

    public int maxUniqueSplit(String s) {
        backtrack(s, 0, new HashSet<>(), 0);
        return best;
    }

    private void backtrack(String s, int start, Set<String> used, int count) {
        int n = s.length();

        // Pruning: can't beat current best
        if (count + (n - start) <= best) return;

        if (start == n) {
            best = Math.max(best, count);
            return;
        }

        for (int end = start + 1; end <= n; end++) {
            String sub = s.substring(start, end);
            if (!used.contains(sub)) {
                used.add(sub);
                backtrack(s, end, used, count + 1);
                used.remove(sub);
            }
        }
    }
}