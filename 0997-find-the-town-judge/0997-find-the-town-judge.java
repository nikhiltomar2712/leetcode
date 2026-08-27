class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] score = new int[n + 1];   // 1-based indexing

        for (int[] t : trust) {
            score[t[0]]--;   // person who trusts loses a point
            score[t[1]]++;   // person who is trusted gains a point
        }

        for (int i = 1; i <= n; i++) {
            // The judge is trusted by everyone else (n-1) and trusts nobody
            if (score[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}