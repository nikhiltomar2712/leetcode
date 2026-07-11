# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution(object):
    def reverseList(self, head):
  # Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
        prev = None
        current = head
        
        while current:
            next_temp = current.next  # Store the next node
            current.next = prev       # Reverse the link
            prev = current            # Move prev forward
            current = next_temp       # Move current forward
        
        return prev  # New head of reversed list