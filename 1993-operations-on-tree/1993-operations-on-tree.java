class LockingTree {
    private int[] parent;
    private List<Integer>[] children;
    private int[] lockedBy;

    public LockingTree(int[] parent) {
        this.parent = parent;
        int n = parent.length;
        children = new List[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) children[parent[i]].add(i);
        lockedBy = new int[n];
    }
    
    public boolean lock(int num, int user) {
        if (lockedBy[num] != 0) return false;
        lockedBy[num] = user;
        return true;
    }
    
    public boolean unlock(int num, int user) {
        if (lockedBy[num] != user) return false;
        lockedBy[num] = 0;
        return true;
    }
    
    public boolean upgrade(int num, int user) {
        if (lockedBy[num] != 0) return false;
        if (hasLockedAncestor(num)) return false;
        if (!hasLockedDescendant(num)) return false;
        lockedBy[num] = user;
        unlockDescendants(num);
        return true;
    }
    
    private boolean hasLockedAncestor(int num) {
        int p = parent[num];
        while (p != -1) {
            if (lockedBy[p] != 0) return true;
            p = parent[p];
        }
        return false;
    }
    
    private boolean hasLockedDescendant(int num) {
        for (int child : children[num]) {
            if (lockedBy[child] != 0 || hasLockedDescendant(child)) return true;
        }
        return false;
    }
    
    private void unlockDescendants(int num) {
        for (int child : children[num]) {
            lockedBy[child] = 0;
            unlockDescendants(child);
        }
    }
}