class Solution(object):
    def rangeBitwiseAnd(self, left, right):
        shift = 0
        # Find the common prefix of left and right
        while left < right:
            left >>= 1
            right >>= 1
            shift += 1
        # Shift back to get the result
        return left << shift
        