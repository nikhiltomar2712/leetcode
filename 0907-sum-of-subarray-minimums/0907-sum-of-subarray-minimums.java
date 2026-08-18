class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        final int MOD = 1_000_000_007;
        
        // left[i] = distance to the previous strictly smaller element
        // right[i] = distance to the next smaller or equal element
        int[] left = new int[n];
        int[] right = new int[n];
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        // Calculate left[i]
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        
        stack.clear();
        
        // Calculate right[i]
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }
        
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long) arr[i] * left[i] * right[i]) % MOD;
        }
        
        return (int) ans;
    }
}