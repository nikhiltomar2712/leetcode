class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        // Let j = number of jumbo burgers
        // Let s = number of small burgers
        // 
        // 4j + 2s = tomatoSlices
        // j + s  = cheeseSlices
        //
        // From second equation: s = cheeseSlices - j
        // Substitute:
        // 4j + 2(cheeseSlices - j) = tomatoSlices
        // 4j + 2*cheeseSlices - 2j = tomatoSlices
        // 2j = tomatoSlices - 2*cheeseSlices
        // j = (tomatoSlices - 2*cheeseSlices) / 2
        
        // j must be a non-negative integer and s must be non-negative
        
        if (tomatoSlices % 2 != 0) {
            return new ArrayList<>();  // tomato must be even
        }
        
        int j = (tomatoSlices - 2 * cheeseSlices) / 2;
        int s = cheeseSlices - j;
        
        if (j >= 0 && s >= 0) {
            return Arrays.asList(j, s);
        }
        
        return new ArrayList<>();
    }
}