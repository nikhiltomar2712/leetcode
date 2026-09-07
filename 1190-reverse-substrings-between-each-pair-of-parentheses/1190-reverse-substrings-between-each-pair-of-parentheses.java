class Solution {
    public String reverseParentheses(String s) {
        StringBuilder stack = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            if (c == ')') {
                // Reverse the content inside the current parentheses
                StringBuilder temp = new StringBuilder();
                while (stack.charAt(stack.length() - 1) != '(') {
                    temp.append(stack.charAt(stack.length() - 1));
                    stack.deleteCharAt(stack.length() - 1);
                }
                // Remove the '('
                stack.deleteCharAt(stack.length() - 1);
                // Append the reversed content
                stack.append(temp);
            } else {
                stack.append(c);
            }
        }
        
        return stack.toString();
    }
}