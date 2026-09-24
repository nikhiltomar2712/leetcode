import java.util.*;

class Solution {
    public int[] frequencySort(int[] nums) {
        // Count frequencies
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.merge(num, 1, Integer::sum);
        }

        // Convert to Integer[] for custom sorting
        Integer[] boxed = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            boxed[i] = nums[i];
        }

        // Sort: frequency ascending, value descending
        Arrays.sort(boxed, (a, b) -> {
            int fa = freq.get(a), fb = freq.get(b);
            if (fa != fb) return fa - fb;      // frequency ascending
            return b - a;                       // value descending
        });

        // Convert back to int[]
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = boxed[i];
        }

        return result;
    }
}