# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class BSTIterator(object):
    def __init__(self, root):
        """
        :type root: TreeNode
        """
        self.stack = []
        self._push_left(root)
    
    def _push_left(self, node):
        """Push all left children of node onto stack."""
        while node:
            self.stack.append(node)
            node = node.left
    
    def next(self):
        """
        :rtype: int
        """
        node = self.stack.pop()
        # If node has a right child, push all left descendants of that right child
        if node.right:
            self._push_left(node.right)
        return node.val
    
    def hasNext(self):
        """
        :rtype: bool
        """
        return len(self.stack) > 0


# Your BSTIterator object will be instantiated and called as such:
# obj = BSTIterator(root)
# param_1 = obj.next()
# param_2 = obj.hasNext()