import java.util.Arrays;

class Solution {
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int n = piles.length / 3;
        int coins = 0;
        // You take piles at indices: n, n+2, n+4, ..., 3n-2
        for (int i = n; i < piles.length; i += 2) {
            coins += piles[i];
        }
        return coins;
    }
}