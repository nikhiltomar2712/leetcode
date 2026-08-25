class Solution(object):
    def reverseBits(self, n):
        result = 0
        for i in range(32):
            # extract i-th bit from right
            bit = (n >> i) & 1
            # put it in the reversed position (31 - i)
            result |= (bit << (31 - i))
        return result