class StockSpanner {
    // Stack to store pairs of (price, span) for previous days
    private Stack<int[]> stack;
    
    public StockSpanner() {
        stack = new Stack<>();
    }
    
    public int next(int price) {
        int span = 1; // Span for the current day starts at 1
        
        // Pop all previous prices that are <= current price
        // and accumulate their spans
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }
        
        // Push current price and its computed span
        stack.push(new int[]{price, span});
        
        return span;
    }
}