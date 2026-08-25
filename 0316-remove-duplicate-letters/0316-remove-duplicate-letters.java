class Solution {
    public String removeDuplicateLetters(String s) {
        // Frequency of each character in the string
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // To keep track of which characters are already in the result
        boolean[] inStack = new boolean[26];
        // Stack to build the result
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            freq[idx]--; // Decrease frequency as we process

            // If character is already in stack, skip it
            if (inStack[idx]) continue;

            // Remove larger characters from stack if they appear later
            while (!stack.isEmpty() && c < stack.peek() && freq[stack.peek() - 'a'] > 0) {
                inStack[stack.pop() - 'a'] = false;
            }

            // Add current character
            stack.push(c);
            inStack[idx] = true;
        }

        // Build result string from stack
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }
}