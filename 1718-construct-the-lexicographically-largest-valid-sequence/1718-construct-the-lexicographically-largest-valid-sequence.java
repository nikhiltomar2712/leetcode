class Solution {
    public int[] constructDistancedSequence(int n) {
        int[] result = new int[2 * n - 1];
        boolean[] used = new boolean[n + 1];
        backtrack(result, used, 0, n);
        return result;
    }

    private boolean backtrack(int[] result, boolean[] used, int pos, int n) {
        if (pos == result.length) return true;

        if (result[pos] != 0) {
            return backtrack(result, used, pos + 1, n);
        }

        for (int num = n; num >= 1; num--) {
            if (used[num]) continue;

            if (num == 1) {
                result[pos] = 1;
                used[1] = true;
                if (backtrack(result, used, pos + 1, n)) return true;
                result[pos] = 0;
                used[1] = false;
            } else {
                int secondPos = pos + num;
                if (secondPos < result.length && result[secondPos] == 0) {
                    result[pos] = num;
                    result[secondPos] = num;
                    used[num] = true;
                    if (backtrack(result, used, pos + 1, n)) return true;
                    result[pos] = 0;
                    result[secondPos] = 0;
                    used[num] = false;
                }
            }
        }

        return false;
    }
}