import java.util.*;

class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        
        int minDiff = Integer.MAX_VALUE;
        List<List<Integer>> result = new ArrayList<>();
        
        // Find the minimum difference and collect pairs in one pass
        for (int i = 1; i < arr.length; i++) {
            int diff = arr[i] - arr[i - 1];
            
            if (diff < minDiff) {
                minDiff = diff;
                result.clear();                 // found a smaller difference
                result.add(Arrays.asList(arr[i - 1], arr[i]));
            } else if (diff == minDiff) {
                result.add(Arrays.asList(arr[i - 1], arr[i]));
            }
        }
        
        return result;
    }
}