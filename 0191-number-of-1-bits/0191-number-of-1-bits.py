class Solution(object):
    def hammingWeight(self, n):
        count = 0
        while n:
            count += n & 1
            n >>= 1
        return count

# Test cases
if __name__ == "__main__":
    sol = Solution()
    print(sol.hammingWeight(11))           # 3
    print(sol.hammingWeight(128))          # 1
    print(sol.hammingWeight(2147483645))   # 30