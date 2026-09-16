class Solution {
    public int maxScore(String s) {
        int totalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') totalOnes++;
        }

        int zerosLeft = 0;
        int onesLeft = 0;
        int best = 0;

        // i goes up to n-2 so the right part is non-empty
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') zerosLeft++;
            else onesLeft++;

            int score = zerosLeft + (totalOnes - onesLeft);
            best = Math.max(best, score);
        }

        return best;
    }
}