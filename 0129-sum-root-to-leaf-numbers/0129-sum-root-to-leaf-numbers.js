// Definition for a binary tree node
function TreeNode(val, left, right) {
    this.val = (val === undefined ? 0 : val);
    this.left = (left === undefined ? null : left);
    this.right = (right === undefined ? null : right);
}

// Solution
var sumNumbers = function(root) {
    if (!root) return 0;
    
    function dfs(node, currentSum) {
        if (!node) return 0;
        
        currentSum = currentSum * 10 + node.val;
        
        if (!node.left && !node.right) {
            return currentSum;
        }
        
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
    
    return dfs(root, 0);
};

// Test cases
function runTests() {
    console.log("=== Sum Root to Leaf Numbers Tests ===\n");
    
    // Test 1: [1,2,3]
    const root1 = new TreeNode(1);
    root1.left = new TreeNode(2);
    root1.right = new TreeNode(3);
    console.log(`Test 1: ${sumNumbers(root1)}`); // Expected: 25
    
    // Test 2: [4,9,0,5,1]
    const root2 = new TreeNode(4);
    root2.left = new TreeNode(9);
    root2.right = new TreeNode(0);
    root2.left.left = new TreeNode(5);
    root2.left.right = new TreeNode(1);
    console.log(`Test 2: ${sumNumbers(root2)}`); // Expected: 1026
    
    // Test 3: Single node [1]
    const root3 = new TreeNode(1);
    console.log(`Test 3: ${sumNumbers(root3)}`); // Expected: 1
    
    // Test 4: [0,1]
    const root4 = new TreeNode(0);
    root4.left = new TreeNode(1);
    console.log(`Test 4: ${sumNumbers(root4)}`); // Expected: 1 (01 = 1)
    
    console.log("\n=== Tests Complete ===");
}

runTests();