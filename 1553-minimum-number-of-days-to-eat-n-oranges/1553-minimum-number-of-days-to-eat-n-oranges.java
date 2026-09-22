import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Integer, Integer> memo = new HashMap<>();

    public int minDays(int n) {
        return dfs(n);
    }

    private int dfs(int n) {
        if (n <= 1) return n;                 // 0 -> 0 days, 1 -> 1 day

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        // Option 1: reach a multiple of 2, then eat n/2
        int byTwo = (n % 2) + 1 + dfs(n / 2);

        // Option 2: reach a multiple of 3, then eat 2n/3
        int byThree = (n % 3) + 1 + dfs(n / 3);

        int result = Math.min(byTwo, byThree);
        memo.put(n, result);
        return result;
    }
}