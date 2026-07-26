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

function printTree(root: TreeNode | null): string[][] {
    // Height of the tree (root height = 0)
    function getHeight(node: TreeNode | null): number {
        if (!node) return -1;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    const height = getHeight(root);
    const m = height + 1;
    const n = (1 << (height + 1)) - 1; // 2^(height+1) - 1

    // Initialize matrix with empty strings
    const res: string[][] = Array.from({ length: m }, () => Array(n).fill(""));

    // Place nodes
    function place(node: TreeNode | null, row: number, col: number): void {
        if (!node) return;

        res[row][col] = String(node.val);

        const offset = 1 << (height - row - 1); // 2^(height - row - 1)

        place(node.left, row + 1, col - offset);
        place(node.right, row + 1, col + offset);
    }

    // Start placing from the middle of the first row
    place(root, 0, (n - 1) >> 1);

    return res;
}