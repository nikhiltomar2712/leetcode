class Solution(object):
    def isAnagram(self, s, t):
        # Check if lengths are equal first
        if len(s) != len(t):
            return False
        
        # Use a dictionary to count characters
        count = {}
        
        # Count characters in s
        for char in s:
            count[char] = count.get(char, 0) + 1
        
        # Subtract characters in t
        for char in t:
            if char not in count or count[char] == 0:
                return False
            count[char] -= 1
        
        return True