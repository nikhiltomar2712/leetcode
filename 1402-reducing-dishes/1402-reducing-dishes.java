import java.util.Arrays;

class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        
        int n = satisfaction.length;
        int sum = 0;    // sum of satisfactions in current suffix
        int total = 0;  // current like-time coefficient sum
        int best = 0;   // cook nothing -> 0
        
        for (int i = n - 1; i >= 0; i--) {
            sum += satisfaction[i];
            total += sum;
            best = Math.max(best, total);
        }
        
        return best;
    }
}