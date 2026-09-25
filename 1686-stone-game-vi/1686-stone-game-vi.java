import java.util.Arrays;

class Solution {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        int[][] stones = new int[n][2];
        
        // Store combined value and original index
        for (int i = 0; i < n; i++) {
            stones[i][0] = aliceValues[i] + bobValues[i];
            stones[i][1] = i;
        }
        
        // Sort by combined value in descending order
        Arrays.sort(stones, (a, b) -> b[0] - a[0]);
        
        int aliceScore = 0, bobScore = 0;
        
        // Alternate turns: Alice takes even indices, Bob takes odd indices
        for (int i = 0; i < n; i++) {
            int idx = stones[i][1];
            if (i % 2 == 0) {
                aliceScore += aliceValues[idx];
            } else {
                bobScore += bobValues[idx];
            }
        }
        
        return Integer.compare(aliceScore, bobScore);
    }
}