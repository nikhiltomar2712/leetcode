class Solution {
    public String minRemoveToMakeValid(String s) {
        char[] chars = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();

        // First pass: mark invalid parentheses
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else if (chars[i] == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();          // matched
                } else {
                    chars[i] = '*';       // unmatched ')'
                }
            }
        }

        // Remaining '(' in stack are unmatched
        while (!stack.isEmpty()) {
            chars[stack.pop()] = '*';
        }

        // Build the result
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (c != '*') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}