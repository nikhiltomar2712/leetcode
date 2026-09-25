import java.util.*;

class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            // Pop larger elements if we can still fill k elements
            while (!stack.isEmpty() 
                   && stack.peekLast() > nums[i] 
                   && stack.size() + (n - i) > k) {
                stack.removeLast();
            }
            // Only add if we haven't reached k elements yet
            if (stack.size() < k) {
                stack.addLast(nums[i]);
            }
        }
        
        // Convert stack to result array
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = stack.pollFirst();
        }
        return result;
    }
}