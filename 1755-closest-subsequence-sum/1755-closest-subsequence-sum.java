import java.util.*;

class Solution {
    public int minAbsDifference(int[] nums, int goal) {
        int n = nums.length;
        int mid = n / 2;
        
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, n);
        
        List<Integer> leftSums = new ArrayList<>();
        List<Integer> rightSums = new ArrayList<>();
        
        generateSubsetSums(left, 0, 0, leftSums);
        generateSubsetSums(right, 0, 0, rightSums);
        
        Collections.sort(rightSums);
        
        int best = Integer.MAX_VALUE;
        
        for (int ls : leftSums) {
            int target = goal - ls;
            // Find closest value to target in rightSums
            int idx = Collections.binarySearch(rightSums, target);
            
            if (idx >= 0) {
                // Exact match found
                return 0;
            } else {
                // idx = -(insertion point) - 1
                int insertPos = -idx - 1;
                
                if (insertPos < rightSums.size()) {
                    best = Math.min(best, Math.abs(ls + rightSums.get(insertPos) - goal));
                }
                if (insertPos > 0) {
                    best = Math.min(best, Math.abs(ls + rightSums.get(insertPos - 1) - goal));
                }
            }
        }
        
        return best;
    }
    
    private void generateSubsetSums(int[] arr, int index, int sum, List<Integer> sums) {
        if (index == arr.length) {
            sums.add(sum);
            return;
        }
        // Exclude arr[index]
        generateSubsetSums(arr, index + 1, sum, sums);
        // Include arr[index]
        generateSubsetSums(arr, index + 1, sum + arr[index], sums);
    }
}