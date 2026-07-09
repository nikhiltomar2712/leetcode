class Solution:
    def maxProfit(self, prices):
        if not prices:
            return 0
        
        # State machine approach
        # buy1: max profit after first buy (negative balance)
        # sell1: max profit after first sell
        # buy2: max profit after second buy
        # sell2: max profit after second sell
        
        buy1 = float('-inf')
        sell1 = 0
        buy2 = float('-inf')
        sell2 = 0
        
        for price in prices:
            buy1 = max(buy1, -price)          # Buy first stock
            sell1 = max(sell1, buy1 + price)  # Sell first stock
            buy2 = max(buy2, sell1 - price)   # Buy second stock
            sell2 = max(sell2, buy2 + price)  # Sell second stock
        
        return sell2