# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def binaryTreePaths(self, root):
        """
        :type root: TreeNode
        :rtype: List[str]
        """
        result = []
        
        def dfs(node, path):
            if not node:
                return
            
            # Append current node to path
            path.append(str(node.val))
            
            # If leaf node, add path to result
            if not node.left and not node.right:
                result.append("->".join(path))
            else:
                # Recurse on children
                dfs(node.left, path)
                dfs(node.right, path)
            
            # Backtrack: remove current node before returning
            path.pop()
        
        dfs(root, [])
        return result