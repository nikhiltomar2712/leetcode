class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int maxDist = 0;
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        
        for (List<Integer> arr : arrays) {
            int currMin = arr.get(0);
            int currMax = arr.get(arr.size() - 1);
            
            // Compare with previous arrays
            if (minVal != Integer.MAX_VALUE) {
                maxDist = Math.max(maxDist, currMax - minVal);
                maxDist = Math.max(maxDist, maxVal - currMin);
            }
            
            // Update global min and max
            minVal = Math.min(minVal, currMin);
            maxVal = Math.max(maxVal, currMax);
        }
        
        return maxDist;
    }
}