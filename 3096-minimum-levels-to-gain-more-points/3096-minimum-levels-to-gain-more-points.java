class Solution {
    public int minimumLevels(int[] possible) {
        int n = possible.length;
        int total = 0;

        // Total score if one player played all levels.
        for (int p : possible) {
            total += (p == 1 ? 1 : -1);
        }

        int alice = 0;

        // Alice must leave at least one level for Bob, so i < n - 1.
        for (int i = 0; i < n - 1; i++) {
            alice += (possible[i] == 1 ? 1 : -1);

            // Alice > Bob  <=>  alice > total - alice  <=>  2 * alice > total
            if (2 * alice > total) {
                return i + 1;
            }
        }

        return -1;
    }
}