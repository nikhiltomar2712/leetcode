class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] indegree = new int[n];
        
        // Count indegrees
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1) {
                indegree[leftChild[i]]++;
                if (indegree[leftChild[i]] > 1) return false; // multiple parents
            }
            if (rightChild[i] != -1) {
                indegree[rightChild[i]]++;
                if (indegree[rightChild[i]] > 1) return false; // multiple parents
            }
        }
        
        // Find the root (exactly one node with indegree 0)
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                if (root != -1) return false; // more than one root
                root = i;
            }
        }
        if (root == -1) return false; // no root (cycle exists)
        
        // BFS to check connectivity and visit all nodes
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        
        queue.offer(root);
        visited[root] = true;
        int count = 0;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;
            
            int left = leftChild[node];
            int right = rightChild[node];
            
            if (left != -1) {
                if (visited[left]) return false; // cycle
                visited[left] = true;
                queue.offer(left);
            }
            if (right != -1) {
                if (visited[right]) return false; // cycle
                visited[right] = true;
                queue.offer(right);
            }
        }
        
        return count == n; // all nodes reachable
    }
}