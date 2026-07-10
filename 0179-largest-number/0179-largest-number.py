class Solution:
    def largestNumber(self, nums):
        # Convert to strings
        nums = [str(x) for x in nums]
        
        # Bubble sort with custom comparison
        for i in range(len(nums)):
            for j in range(i + 1, len(nums)):
                if nums[i] + nums[j] < nums[j] + nums[i]:
                    nums[i], nums[j] = nums[j], nums[i]
        
        # Join and handle zeros
        result = ''.join(nums)
        return '0' if result[0] == '0' else result