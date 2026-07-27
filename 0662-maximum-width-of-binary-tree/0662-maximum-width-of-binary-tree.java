class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        
        int maxWidth = 0;
        Queue<Pair<TreeNode, Long>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0L));  // node + its position index
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            long leftmost = queue.peek().getValue();
            long rightmost = leftmost;
            
            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Long> curr = queue.poll();
                TreeNode node = curr.getKey();
                long idx = curr.getValue();
                
                rightmost = idx;
                
                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, 2 * idx));
                }
                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, 2 * idx + 1));
                }
            }
            
            maxWidth = Math.max(maxWidth, (int)(rightmost - leftmost + 1));
        }
        
        return maxWidth;
    }
}