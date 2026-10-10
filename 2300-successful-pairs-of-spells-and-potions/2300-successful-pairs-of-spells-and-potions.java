class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length;
        int m = potions.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            long need = (success + spells[i] - 1) / spells[i];
            int lo = 0, hi = m;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (potions[mid] >= need) hi = mid;
                else lo = mid + 1;
            }
            res[i] = m - lo;
        }
        return res;
    }
}