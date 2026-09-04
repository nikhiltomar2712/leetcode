class SnapshotArray {
    private List<int[]>[] history; // Array of lists to store (snap_id, value) pairs
    private int currSnapId = 0;
    
    public SnapshotArray(int length) {
        // Initialize each index with a list containing the initial state (0 at snap_id 0)
        history = new List[length];
        for (int i = 0; i < length; i++) {
            history[i] = new ArrayList<>();
            history[i].add(new int[]{0, 0}); // (snap_id, value) for initial state
        }
    }
    
    public void set(int index, int val) {
        List<int[]> changes = history[index];
        int[] lastEntry = changes.get(changes.size() - 1);
        
        // Only add a new entry if the value is different from the last snapshot
        if (lastEntry[1] != val) {
            changes.add(new int[]{currSnapId, val});
        }
    }
    
    public int snap() {
        return currSnapId++;
    }
    
    public int get(int index, int snap_id) {
        List<int[]> changes = history[index];
        
        // Binary search for the largest snap_id <= snap_id
        int left = 0, right = changes.size() - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (changes.get(mid)[0] <= snap_id) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        
        // The entry at 'left' has the largest snap_id <= target snap_id
        return changes.get(left)[1];
    }
}