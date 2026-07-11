# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution(object):
    def removeElements(self, head, val):
   # Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
        # Create a dummy node that points to the head
        dummy = ListNode(0)
        dummy.next = head
        
        # Use a pointer to traverse the list
        current = dummy
        
        while current.next:
            if current.next.val == val:
                # Skip the node with the target value
                current.next = current.next.next
            else:
                # Move to the next node only when not removing
                current = current.next
        
        return dummy.next