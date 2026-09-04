class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[][] count = new int[10][10];
        int pairs = 0;
        
        for (int[] domino : dominoes) {
            int a = domino[0], b = domino[1];
            int min = Math.min(a, b);
            int max = Math.max(a, b);
            
            pairs += count[min][max];
            count[min][max]++;
        }
        
        return pairs;
    }
}