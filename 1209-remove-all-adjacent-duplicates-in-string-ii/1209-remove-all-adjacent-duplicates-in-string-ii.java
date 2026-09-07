import java.util.*;

class Solution {
    public String removeDuplicates(String s, int k) {
        // Stack stores pairs: [character, count]
        Deque<int[]> stack = new ArrayDeque<>();
        
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek()[0] == c) {
                // Same character as top → increase count
                stack.peek()[1]++;
                // If count reaches k, remove it
                if (stack.peek()[1] == k) {
                    stack.pop();
                }
            } else {
                // Different character → push new pair
                stack.push(new int[]{c, 1});
            }
        }
        
        // Build the result from the stack
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            int[] top = stack.removeLast(); // remove from bottom to keep order
            for (int i = 0; i < top[1]; i++) {
                sb.append((char) top[0]);
            }
        }
        
        return sb.toString();
    }
}