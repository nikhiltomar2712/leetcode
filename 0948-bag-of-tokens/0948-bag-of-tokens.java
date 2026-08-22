class Solution {
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int left = 0, right = tokens.length - 1;
        int score = 0, maxScore = 0;
        
        while (left <= right) {
            if (power >= tokens[left]) {
                // Play face-up: spend power, gain score
                power -= tokens[left++];
                score++;
                maxScore = Math.max(maxScore, score);
            } else if (score > 0) {
                // Play face-down: gain power, lose score
                power += tokens[right--];
                score--;
            } else {
                break; // Cannot make any more moves
            }
        }
        
        return maxScore;
    }
}