class Solution(object):
    def singleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        # Step 1: XOR all numbers → a ^ b
        xor_all = 0
        for num in nums:
            xor_all ^= num
        
        # Step 2: Find a set bit in xor_all (rightmost set bit)
        # Using xor_all & -xor_all isolates the rightmost set bit
        diff_bit = xor_all & -xor_all
        
        # Step 3: Split numbers into two groups and XOR each
        a = 0
        b = 0
        for num in nums:
            if num & diff_bit:
                a ^= num
            else:
                b ^= num
        
        return [a, b]