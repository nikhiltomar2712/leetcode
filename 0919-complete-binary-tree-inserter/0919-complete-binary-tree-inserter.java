/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class CBTInserter {
    private TreeNode root;
    private Deque<TreeNode> deque; // To keep track of nodes that can have children

    public CBTInserter(TreeNode root) {
        this.root = root;
        deque = new LinkedList<>();
        
        // Level order traversal to initialize the deque
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null || node.right == null) {
                deque.offer(node); // Node can accept a new child
            }
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }
    
    public int insert(int val) {
        TreeNode parent = deque.peekFirst(); // First node in deque is the parent
        TreeNode newNode = new TreeNode(val);
        
        // Attach newNode to the appropriate side
        if (parent.left == null) {
            parent.left = newNode;
        } else { // parent.right == null
            parent.right = newNode;
            deque.pollFirst(); // Parent now has both children, remove it
        }
        
        // The new node is added to the end of deque (as it can now have children)
        deque.offerLast(newNode);
        
        return parent.val;
    }
    
    public TreeNode get_root() {
        return root;
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */