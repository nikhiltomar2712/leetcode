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
 * @param {number} key
 * @return {TreeNode}
 */
var deleteNode = function(root, key) {
    // Base case: tree is empty or key not found
    if (!root) return null;

    // Search for the node to delete
    if (key < root.val) {
        root.left = deleteNode(root.left, key);
        return root;
    } else if (key > root.val) {
        root.right = deleteNode(root.right, key);
        return root;
    }

    // Found the node to delete (root.val === key)
    // Case 1: Node has no children (leaf node)
    if (!root.left && !root.right) {
        return null;
    }

    // Case 2: Node has only one child
    if (!root.left) {
        return root.right;
    }
    if (!root.right) {
        return root.left;
    }

    // Case 3: Node has two children
    // Find the inorder successor (smallest in the right subtree)
    let successor = findMin(root.right);
    // Replace current node's value with successor's value
    root.val = successor.val;
    // Delete the successor from the right subtree
    root.right = deleteNode(root.right, successor.val);

    return root;
};

/**
 * Helper function to find the node with the minimum value in a BST
 * @param {TreeNode} node
 * @return {TreeNode}
 */
function findMin(node) {
    while (node.left) {
        node = node.left;
    }
    return node;
}