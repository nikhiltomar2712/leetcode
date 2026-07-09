class Solution:
    def isPalindrome(self, s):
        left, right = 0, len(s) - 1
        
        while left < right:
            # Skip non-alphanumeric characters from left
            while left < right and not s[left].isalnum():
                left += 1
            
            # Skip non-alphanumeric characters from right
            while left < right and not s[right].isalnum():
                right -= 1
            
            # Compare characters (case-insensitive)
            if s[left].lower() != s[right].lower():
                return False
            
            left += 1
            right -= 1
        
        return True


# Test the code
if __name__ == "__main__":
    solution = Solution()
    
    # Example 1
    s1 = "A man, a plan, a canal: Panama"
    print(solution.isPalindrome(s1))
    
    # Example 2
    s2 = "race a car"
    print(solution.isPalindrome(s2))
    
    # Example 3
    s3 = " "
    print(solution.isPalindrome(s3))