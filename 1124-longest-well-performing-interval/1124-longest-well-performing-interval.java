class Solution {
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] prefix = new int[n + 1];
        
        // Build prefix sum with +1 for >8, -1 for <=8
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (hours[i] > 8 ? 1 : -1);
        }
        
        int maxLength = 0;
        // Use a stack to store indices where prefix sum is decreasing
        Deque<Integer> stack = new ArrayDeque<>();
        
        // Build decreasing stack (strictly decreasing prefix sums)
        for (int i = 0; i <= n; i++) {
            if (stack.isEmpty() || prefix[i] < prefix[stack.peek()]) {
                stack.push(i);
            }
        }
        
        // Traverse from right to left
        for (int i = n; i >= 0; i--) {
            while (!stack.isEmpty() && prefix[i] > prefix[stack.peek()]) {
                maxLength = Math.max(maxLength, i - stack.pop());
            }
        }
        
        return maxLength;
    }
}