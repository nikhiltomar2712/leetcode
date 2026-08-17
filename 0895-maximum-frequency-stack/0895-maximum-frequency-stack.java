class FreqStack {
    // Map to store the frequency of each value
    private Map<Integer, Integer> freqMap;
    // Map to store stacks of values for each frequency level
    private Map<Integer, Stack<Integer>> freqStacks;
    // Variable to track the current maximum frequency
    private int maxFreq;

    public FreqStack() {
        freqMap = new HashMap<>();
        freqStacks = new HashMap<>();
        maxFreq = 0;
    }
    
    public void push(int val) {
        // Update frequency of val
        int freq = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, freq);
        
        // Update max frequency if needed
        maxFreq = Math.max(maxFreq, freq);
        
        // Push val onto the stack corresponding to its new frequency
        freqStacks.computeIfAbsent(freq, k -> new Stack<>()).push(val);
    }
    
    public int pop() {
        // Get the stack with the highest frequency
        Stack<Integer> maxStack = freqStacks.get(maxFreq);
        int val = maxStack.pop();
        
        // Decrease frequency of the popped value
        freqMap.put(val, freqMap.get(val) - 1);
        
        // If the max frequency stack becomes empty, decrease maxFreq
        if (maxStack.isEmpty()) {
            maxFreq--;
        }
        
        return val;
    }
}