class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int n = prices.length;
        
        // State definitions:
        // hold[i] = max profit on day i while holding a stock
        // sold[i] = max profit on day i after selling a stock (cooldown day)
        // rest[i] = max profit on day i while not holding a stock and not in cooldown
        
        int[] hold = new int[n];
        int[] sold = new int[n];
        int[] rest = new int[n];
        
        // Initialize day 0
        hold[0] = -prices[0];  // Buy stock on day 0
        sold[0] = 0;            // Cannot sell on day 0
        rest[0] = 0;            // No transaction on day 0
        
        for (int i = 1; i < n; i++) {
            // Hold: either continue holding from previous day, or buy today after resting
            hold[i] = Math.max(hold[i - 1], rest[i - 1] - prices[i]);
            
            // Sold: sell the stock held yesterday
            sold[i] = hold[i - 1] + prices[i];
            
            // Rest: either continue resting, or rest after selling
            rest[i] = Math.max(rest[i - 1], sold[i - 1]);
        }
        
        // Max profit is either resting or selling on the last day
        return Math.max(rest[n - 1], sold[n - 1]);
    }
}