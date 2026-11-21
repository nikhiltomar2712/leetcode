class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        
        // Build original tree
        List<List<Integer>> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            children.get(parent[i]).add(i);
        }
        
        // New parent array after changes
        int[] newParent = new int[n];
        Arrays.fill(newParent, -1);
        newParent[0] = -1; // root has no parent
        
        // DFS to find closest ancestor with same character
        // lastSeen[char] = most recent node with that character on current path
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        
        dfs(0, children, s, newParent, lastSeen, -1);
        
        // Build new tree from newParent
        List<List<Integer>> newChildren = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            newChildren.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            if (newParent[i] != -1) {
                newChildren.get(newParent[i]).add(i);
            }
        }
        
        // Compute subtree sizes
        int[] answer = new int[n];
        computeSize(0, newChildren, answer);
        
        return answer;
    }
    
    private void dfs(int node, List<List<Integer>> children, String s, 
                     int[] newParent, int[] lastSeen, int parent) {
        char c = s.charAt(node);
        int charIdx = c - 'a';
        
        // Find closest ancestor with same character
        if (lastSeen[charIdx] != -1) {
            newParent[node] = lastSeen[charIdx];
        } else {
            newParent[node] = parent; // keep original parent
        }
        
        // Save old value and update
        int oldLastSeen = lastSeen[charIdx];
        lastSeen[charIdx] = node;
        
        // Process children
        for (int child : children.get(node)) {
            dfs(child, children, s, newParent, lastSeen, node);
        }
        
        // Backtrack
        lastSeen[charIdx] = oldLastSeen;
    }
    
    private int computeSize(int node, List<List<Integer>> children, int[] answer) {
        int size = 1;
        for (int child : children.get(node)) {
            size += computeSize(child, children, answer);
        }
        answer[node] = size;
        return size;
    }
}