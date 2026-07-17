/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */

/**
 * Encodes a tree to a single string.
 *
 * @param {TreeNode} root
 * @return {string}
 */
var serialize = function(root) {
    const res = [];
    serializeHelper(root, res);
    return res.join(' ');
};

function serializeHelper(root, res) {
    if (!root) return;
    res.push(root.val);
    serializeHelper(root.left, res);
    serializeHelper(root.right, res);
};

/**
 * Decodes your encoded data to tree.
 *
 * @param {string} data
 * @return {TreeNode}
 */
var deserialize = function(data) {
    if (!data || data.trim() === '') return null;
    const nodes = data.split(' ').map(Number);
    let index = 0;
    
    return deserializeHelper(nodes, () => {
        return nodes[index++];
    }, Number.MIN_SAFE_INTEGER, Number.MAX_SAFE_INTEGER);
};

function deserializeHelper(nodes, getNext, min, max) {
    if (index >= nodes.length) return null; // Use global index or pass by ref
    
    const val = getNext();
    if (val < min || val > max) {
        index--; // rollback
        return null;
    }
    
    const root = new TreeNode(val);
    root.left = deserializeHelper(nodes, getNext, min, val);
    root.right = deserializeHelper(nodes, getNext, val, max);
    return root;
}

// Fix: better index management
var deserialize = function(data) {
    if (!data) return null;
    const nodes = data.trim().split(/\s+/).map(Number);
    let i = 0;
    
    const build = (min, max) => {
        if (i >= nodes.length) return null;
        const val = nodes[i];
        if (val < min || val > max) return null;
        i++;
        const node = new TreeNode(val);
        node.left = build(min, val);
        node.right = build(val, max);
        return node;
    };
    
    return build(Number.MIN_SAFE_INTEGER, Number.MAX_SAFE_INTEGER);
};