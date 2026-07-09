# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def maxPathSum(self, root):
        """
        Find the maximum path sum in a binary tree.
        
        Args:
            root: Root node of the binary tree
            
        Returns:
            Maximum path sum
        """
        self.max_sum = float('-inf')  # Initialize with smallest possible value
        
        def dfs(node):
            """
            Returns the maximum sum of a path starting from current node 
            and going downwards (straight path, no branching).
            Updates the global maximum path sum that can go through current node.
            """
            if not node:
                return 0
            
            # Get max sum from left and right subtrees
            # If they're negative, we can skip them (take 0 instead)
            left_sum = max(dfs(node.left), 0)
            right_sum = max(dfs(node.right), 0)
            
            # Path that goes through current node (left -> node -> right)
            # This is a candidate for the global maximum
            current_path_sum = node.val + left_sum + right_sum
            
            # Update global maximum
            self.max_sum = max(self.max_sum, current_path_sum)
            
            # Return the best straight path starting from current node
            # (can only go to one child to maintain straight path)
            return node.val + max(left_sum, right_sum)
        
        dfs(root)
        return self.max_sum