class Solution(object):
    def moveZeroes(self, nums):
        non_zero = 0
        
        for i in range(len(nums)):
            if nums[i] != 0:
                if i != non_zero:
                    nums[non_zero], nums[i] = nums[i], nums[non_zero]
                non_zero += 1