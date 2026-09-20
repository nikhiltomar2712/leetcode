class Solution {
    public int getWinner(int[] arr, int k) {
        int n = arr.length;
        int winner = arr[0];
        int consecutiveWins = 0;

        for (int i = 1; i < n; i++) {
            if (arr[i] > winner) {
                // New larger number becomes the winner
                winner = arr[i];
                consecutiveWins = 1;
            } else {
                // Current winner stays
                consecutiveWins++;
            }

            // Found a number that won k consecutive rounds
            if (consecutiveWins == k) {
                return winner;
            }
        }

        // If we finish the array without anyone reaching k wins,
        // the maximum element is the winner (it will keep winning forever)
        return winner;
    }
}