class Solution(object):
    def missingNumber(self, nums):
        n = len(nums)
        missing = n
        
        for i in range(n):
            missing ^= i ^ nums[i]
        
        return missing