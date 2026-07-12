class Solution(object):                          # Level 0
    def isPalindrome(self, head):                # Level 1 (4 spaces)
        if not head or not head.next:            # Level 2 (8 spaces)
            return True                          # Level 3 (12 spaces)
        
        slow = head                              # Level 2
        fast = head                              # Level 2
        while fast and fast.next:                # Level 2
            slow = slow.next                     # Level 3
            fast = fast.next.next                # Level 3
        
        prev = None                              # Level 2
        while slow:                              # Level 2
            next_node = slow.next                # Level 3
            slow.next = prev                     # Level 3
            prev = slow                          # Level 3
            slow = next_node                     # Level 3
        
        left = head                              # Level 2
        right = prev                             # Level 2
        while right:                             # Level 2
            if left.val != right.val:            # Level 3
                return False                     # Level 4
            left = left.next                     # Level 3
            right = right.next                   # Level 3
        
        return True                              # Level 2