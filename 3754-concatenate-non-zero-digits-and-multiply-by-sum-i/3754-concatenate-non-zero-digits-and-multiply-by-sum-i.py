class Solution(object):
    def sumAndMultiply(self, n):
        if n == 0:
            return 0
        
        # Collect non-zero digits in order (from left to right, but we'll build x properly)
        digits = []
        temp = n
        while temp > 0:
            digit = temp % 10
            if digit != 0:
                digits.append(digit)
            temp //= 10
        
        if not digits:
            return 0
        
        # Reverse because we collected from right to left
        digits.reverse()
        
        # Build x by concatenating
        x = 0
        for d in digits:
            x = x * 10 + d
        
        # Sum of digits in x (which are the non-zero digits)
        digit_sum = sum(digits)
        
        # Return x * digit_sum
        return x * digit_sum