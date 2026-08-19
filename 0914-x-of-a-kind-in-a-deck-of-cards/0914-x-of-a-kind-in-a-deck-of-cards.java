class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        // Count frequency of each number
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int card : deck) {
            countMap.put(card, countMap.getOrDefault(card, 0) + 1);
        }
        
        // Find the minimum frequency (smallest group size)
        int minCount = Integer.MAX_VALUE;
        for (int count : countMap.values()) {
            minCount = Math.min(minCount, count);
        }
        
        // If any count is less than 2, impossible (since x > 1)
        if (minCount < 2) return false;
        
        // For each possible group size x from 2 to minCount
        for (int x = 2; x <= minCount; x++) {
            // Check if all counts are divisible by x
            boolean allDivisible = true;
            for (int count : countMap.values()) {
                if (count % x != 0) {
                    allDivisible = false;
                    break;
                }
            }
            if (allDivisible) {
                return true;
            }
        }
        
        return false;
    }
}