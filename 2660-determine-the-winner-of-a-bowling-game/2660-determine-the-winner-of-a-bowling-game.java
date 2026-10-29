class Solution {
    public int isWinner(int[] player1, int[] player2) {
        int s1 = score(player1);
        int s2 = score(player2);
        if (s1 > s2) return 1;
        if (s2 > s1) return 2;
        return 0;
    }

    private int score(int[] player) {
        int total = 0;
        for (int i = 0; i < player.length; i++) {
            int val = player[i];
            if (i > 0 && player[i - 1] == 10) val *= 2;
            else if (i > 1 && player[i - 2] == 10) val *= 2;
            total += val;
        }
        return total;
    }
}