import java.util.*;

class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] players = new int[n][2];
        for (int i = 0; i < n; i++) {
            players[i][0] = ages[i];
            players[i][1] = scores[i];
        }

        // Sort by age, then by score (both ascending)
        Arrays.sort(players, (p1, p2) -> {
            if (p1[0] != p2[0]) return p1[0] - p2[0];
            return p1[1] - p2[1];
        });

        int[] dp = new int[n];
        int maxScore = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = players[i][1];  // start with just this player
            for (int j = 0; j < i; j++) {
                // Valid if the earlier player's score <= current player's score
                if (players[j][1] <= players[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + players[i][1]);
                }
            }
            maxScore = Math.max(maxScore, dp[i]);
        }

        return maxScore;
    }
}