class Solution {
    public int maxHappyGroups(int batchSize, int[] groups) {
        int[] count = new int[batchSize];
        int res = 0;
        for (int g : groups) {
            int r = g % batchSize;
            if (r == 0) res++;
            else if (count[batchSize - r] > 0) {
                count[batchSize - r]--;
                res++;
            } else {
                count[r]++;
            }
        }
        Map<String, Integer> memo = new HashMap<>();
        return res + dfs(count, 0, batchSize, memo);
    }

    private int dfs(int[] count, int remainder, int batchSize, Map<String, Integer> memo) {
        String key = Arrays.toString(count) + "," + remainder;
        if (memo.containsKey(key)) return memo.get(key);
        int best = 0;
        for (int i = 1; i < batchSize; i++) {
            if (count[i] == 0) continue;
            count[i]--;
            int cur = dfs(count, (remainder + i) % batchSize, batchSize, memo) + (remainder == 0 ? 1 : 0);
            count[i]++;
            best = Math.max(best, cur);
        }
        memo.put(key, best);
        return best;
    }
}