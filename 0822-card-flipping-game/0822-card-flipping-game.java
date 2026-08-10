class Solution {
    public int flipgame(int[] fronts, int[] backs) {
        int n = fronts.length;
        
        // Step 1: Find numbers that appear on both sides of the same card
        Set<Integer> impossible = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (fronts[i] == backs[i]) {
                impossible.add(fronts[i]);
            }
        }
        
        // Step 2: Find the minimum number that is NOT impossible
        int minGood = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // Check front side
            if (!impossible.contains(fronts[i])) {
                minGood = Math.min(minGood, fronts[i]);
            }
            // Check back side
            if (!impossible.contains(backs[i])) {
                minGood = Math.min(minGood, backs[i]);
            }
        }
        
        // Step 3: Return result
        return minGood == Integer.MAX_VALUE ? 0 : minGood;
    }
}