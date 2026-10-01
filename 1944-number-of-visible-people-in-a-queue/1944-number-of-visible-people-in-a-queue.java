class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            int count = 0;
            while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
                stack.pop();
                count++;
            }
            if (!stack.isEmpty()) count++;
            res[i] = count;
            stack.push(i);
        }
        return res;
    }
}