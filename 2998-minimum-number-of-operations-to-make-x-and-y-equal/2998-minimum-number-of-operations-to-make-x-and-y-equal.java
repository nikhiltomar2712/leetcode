class Solution {
    private Map<Integer, Integer> memo = new HashMap<>();

    public int minimumOperationsToMakeEqual(int x, int y) {
        if (x <= y) {
            return y - x;
        }
        if (memo.containsKey(x)) {
            return memo.get(x);
        }

        int res = x - y;

        res = Math.min(res, (x % 5) + 1 + minimumOperationsToMakeEqual(x / 5, y));
        res = Math.min(res, (5 - (x % 5)) + 1 + minimumOperationsToMakeEqual(x / 5 + 1, y));

        res = Math.min(res, (x % 11) + 1 + minimumOperationsToMakeEqual(x / 11, y));
        res = Math.min(res, (11 - (x % 11)) + 1 + minimumOperationsToMakeEqual(x / 11 + 1, y));

        memo.put(x, res);
        return res;
    }
}