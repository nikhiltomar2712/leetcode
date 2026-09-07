import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 0;
        
        for (int num : arr) {
            // Length ending at current number = length ending at (num - difference) + 1
            int currLen = dp.getOrDefault(num - difference, 0) + 1;
            dp.put(num, currLen);
            maxLen = Math.max(maxLen, currLen);
        }
        
        return maxLen;
    }
}