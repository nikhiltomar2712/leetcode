 # Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def lowestCommonAncestor(self, root, p, q):
        """
        :type root: TreeNode
        :type p: TreeNode
        :type q: TreeNode
        :rtype: TreeNode
        """
        # Start from the root
        current = root
        
        while current:
            # Both nodes are in the left subtree
            if p.val < current.val and q.val < current.val:
                current = current.left
            # Both nodes are in the right subtree
            elif p.val > current.val and q.val > current.val:
                current = current.right
            else:
                # Current node is the LCA (one on left, one on right, or current == p or q)
                return current
        
        return None  # Should not reach here as p and q are guaranteed to exist