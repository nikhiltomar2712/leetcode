class MajorityChecker {
    private int[] arr;
    private Map<Integer, List<Integer>> numToIndices;
    private Random rand;
    private static final int TRIALS = 20;

    public MajorityChecker(int[] arr) {
        this.arr = arr;
        this.numToIndices = new HashMap<>();
        this.rand = new Random();
        
        for (int i = 0; i < arr.length; i++) {
            numToIndices.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }
    
    public int query(int left, int right, int threshold) {
        for (int t = 0; t < TRIALS; t++) {
            int randIdx = left + rand.nextInt(right - left + 1);
            int candidate = arr[randIdx];
            
            List<Integer> indices = numToIndices.get(candidate);
            int count = upperBound(indices, right) - lowerBound(indices, left);
            
            if (count >= threshold) {
                return candidate;
            }
        }
        return -1;
    }
    
    // First index >= target
    private int lowerBound(List<Integer> list, int target) {
        int lo = 0, hi = list.size();
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (list.get(mid) >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
    
    // First index > target
    private int upperBound(List<Integer> list, int target) {
        int lo = 0, hi = list.size();
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (list.get(mid) > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}