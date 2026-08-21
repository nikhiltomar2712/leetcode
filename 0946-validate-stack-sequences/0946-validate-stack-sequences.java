class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0; // pointer for popped array
        
        for (int x : pushed) {
            stack.push(x);
            
            // Keep popping while the top of the stack matches the next expected pop
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        
        // If we successfully matched all elements in popped, the stack should be empty
        return stack.isEmpty();
        // or equivalently: return j == popped.length;
    }
}