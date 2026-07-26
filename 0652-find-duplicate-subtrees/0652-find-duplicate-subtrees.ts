/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function findDuplicateSubtrees(root: TreeNode | null): Array<TreeNode | null> {
    const result: TreeNode[] = [];
    const count = new Map<string, number>();

    function serialize(node: TreeNode | null): string {
        if (!node) return "#";

        const key = `${node.val},${serialize(node.left)},${serialize(node.right)}`;

        const freq = (count.get(key) || 0) + 1;
        count.set(key, freq);

        // Add the node only the first time we detect a duplicate
        if (freq === 2) {
            result.push(node);
        }

        return key;
    }

    serialize(root);
    return result;
}