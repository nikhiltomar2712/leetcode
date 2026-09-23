class Solution {
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int waiting = 0;
        int profit = 0;
        int bestProfit = 0;
        int bestRotations = -1;
        int rotations = 0;
        int i = 0;
        int n = customers.length;

        // Process all arrivals, then continue while waiting > 0
        while (i < n || waiting > 0) {
            if (i < n) {
                waiting += customers[i++];
            }
            int boarded = Math.min(4, waiting);
            waiting -= boarded;
            rotations++;
            profit += boarded * boardingCost - runningCost;

            if (profit > bestProfit) {
                bestProfit = profit;
                bestRotations = rotations;
            }

            // Optimization: if boarding 4 yields no more profit, stop
            if (waiting == 0 && i >= n) break;
        }

        return bestProfit > 0 ? bestRotations : -1;
    }
}