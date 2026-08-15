class ExamRoom {
    private TreeSet<int[]> intervals; // intervals stored as [left, right]
    private Map<Integer, Integer> left;  // seat -> left neighbor
    private Map<Integer, Integer> right; // seat -> right neighbor
    private int n;

    public ExamRoom(int n) {
        this.n = n;
        // Custom comparator: larger distance first, then smaller left endpoint
        intervals = new TreeSet<>((a, b) -> {
            int d1 = dist(a);
            int d2 = dist(b);
            if (d1 != d2) {
                return d2 - d1; // max distance first
            }
            return a[0] - b[0]; // smaller start first
        });
        left = new HashMap<>();
        right = new HashMap<>();
        
        // Initial empty interval covering the whole room
        addInterval(new int[]{-1, n});
    }
    
    public int seat() {
        int[] longest = intervals.first();
        int l = longest[0];
        int r = longest[1];
        
        int seat;
        if (l == -1) {
            seat = 0;
        } else if (r == n) {
            seat = n - 1;
        } else {
            seat = (l + r) / 2;
        }
        
        // Split the interval
        removeInterval(longest);
        addInterval(new int[]{l, seat});
        addInterval(new int[]{seat, r});
        
        return seat;
    }
    
    public void leave(int p) {
        int l = left.get(p);
        int r = right.get(p);
        
        // Remove the two small intervals
        removeInterval(new int[]{l, p});
        removeInterval(new int[]{p, r});
        
        // Merge into one big interval
        addInterval(new int[]{l, r});
    }
    
    // Distance that a student would get by sitting in this interval
    private int dist(int[] interval) {
        int l = interval[0];
        int r = interval[1];
        if (l == -1) return r;
        if (r == n) return n - 1 - l;
        return (r - l) / 2;
    }
    
    private void addInterval(int[] interval) {
        intervals.add(interval);
        left.put(interval[1], interval[0]);
        right.put(interval[0], interval[1]);
    }
    
    private void removeInterval(int[] interval) {
        intervals.remove(interval);
        left.remove(interval[1]);
        right.remove(interval[0]);
    }
}