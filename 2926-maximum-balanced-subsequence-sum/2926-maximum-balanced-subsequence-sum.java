import java.util.TreeMap;
import java.util.Map;

class Solution {
    public long maxBalancedSubsequenceSum(int[] nums) {
        long result = Long.MIN_VALUE;
        // Key: nums[i] - i, Value: maximum sum of a balanced subsequence ending with this key
        TreeMap<Integer, Long> treeMap = new TreeMap<>();

        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, nums[i]); // Single element is always balanced

            if (nums[i] > 0) { // Only positive numbers can improve the sum
                int key = nums[i] - i;
                long currentSum = nums[i];

                // Find the best valid predecessor (largest key <= current key)
                Map.Entry<Integer, Long> floor = treeMap.floorEntry(key);
                if (floor != null) {
                    currentSum += floor.getValue();
                }

                // Prune: remove entries with larger keys but smaller/equal sums
                // They are strictly worse than the current state
                while (treeMap.ceilingKey(key) != null && 
                       treeMap.ceilingEntry(key).getValue() <= currentSum) {
                    treeMap.remove(treeMap.ceilingKey(key));
                }

                treeMap.put(key, currentSum);
                result = Math.max(result, currentSum);
            }
        }

        return result;
    }
}