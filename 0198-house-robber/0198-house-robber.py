class Solution(object):
    def rob(self, nums):
        prev2 = 0  # dp[i-2]
        prev1 = 0  # dp[i-1]
        
        for num in nums:
            current = max(prev1, prev2 + num)
            prev2 = prev1
            prev1 = current
        
        return prev1