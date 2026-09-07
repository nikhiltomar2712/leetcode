class Solution {
    public int minCostToMoveChips(int[] position) {
        int even = 0;
        int odd = 0;
        
        for (int pos : position) {
            if (pos % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        
        // Cost of moving all to even position = number of odd chips
        // Cost of moving all to odd position = number of even chips
        return Math.min(even, odd);
    }
}