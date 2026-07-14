class Solution(object):
    def nthUglyNumber(self, n):
        ugly = [1] * n
        i2 = i3 = i5 = 0
        next_multiple_of_2 = 2
        next_multiple_of_3 = 3
        next_multiple_of_5 = 5
        
        for i in range(1, n):
            # Choose the smallest next ugly number
            next_ugly = min(next_multiple_of_2, next_multiple_of_3, next_multiple_of_5)
            ugly[i] = next_ugly
            
            # Increment pointers that generated this value
            if next_ugly == next_multiple_of_2:
                i2 += 1
                next_multiple_of_2 = ugly[i2] * 2
            if next_ugly == next_multiple_of_3:
                i3 += 1
                next_multiple_of_3 = ugly[i3] * 3
            if next_ugly == next_multiple_of_5:
                i5 += 1
                next_multiple_of_5 = ugly[i5] * 5
        
        return ugly[n-1]