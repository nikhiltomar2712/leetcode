class Solution {
    private int M; // Number of valid (non-blacklisted) numbers
    private Map<Integer, Integer> map; // Maps blacklisted numbers to whitelisted ones
    private Random rand;

    public Solution(int n, int[] blacklist) {
        map = new HashMap<>();
        rand = new Random();
        
        // M is the count of valid numbers = n - blacklist.length
        M = n - blacklist.length;
        
        // Add all blacklisted numbers to a set for quick lookup
        Set<Integer> blackSet = new HashSet<>();
        for (int b : blacklist) {
            blackSet.add(b);
        }
        
        // The first M numbers in [0, n-1] are our "mapping range"
        // We want to map blacklisted numbers in this range to whitelisted numbers
        // in the range [M, n-1]
        int last = M; // Start from the first number after the valid range
        
        for (int b : blacklist) {
            // Only map blacklisted numbers that are in the valid range [0, M-1]
            if (b < M) {
                // Find a whitelisted number to map to
                while (blackSet.contains(last)) {
                    last++;
                }
                map.put(b, last);
                last++;
            }
        }
    }
    
    public int pick() {
        int r = rand.nextInt(M); // Random number in [0, M-1]
        // If r is blacklisted, return its mapped value, otherwise return r
        return map.getOrDefault(r, r);
    }
}