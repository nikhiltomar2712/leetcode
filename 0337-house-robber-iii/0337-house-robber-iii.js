/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number}
 */
var rob = function(root) {
    // Returns [robThisNode, skipThisNode]
    function dfs(node) {
        if (!node) return [0, 0];
        
        const left = dfs(node.left);
        const right = dfs(node.right);
        
        // If we rob this node, we can't rob its children
        const robNode = node.val + left[1] + right[1];
        
        // If we skip this node, we take max of rob/skip for each child
        const skipNode = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        
        return [robNode, skipNode];
    }
    
    const result = dfs(root);
    return Math.max(result[0], result[1]);
};