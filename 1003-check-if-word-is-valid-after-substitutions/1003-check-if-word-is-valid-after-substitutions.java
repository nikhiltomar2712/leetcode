class Solution {
    public boolean isValid(String s) {
        // Length must be a multiple of 3
        if (s.length() % 3 != 0) {
            return false;
        }

        StringBuilder stack = new StringBuilder();

        for (char c : s.toCharArray()) {
            stack.append(c);

            // Check if the last three characters form "abc"
            int len = stack.length();
            if (len >= 3 &&
                stack.charAt(len - 3) == 'a' &&
                stack.charAt(len - 2) == 'b' &&
                stack.charAt(len - 1) == 'c') {
                stack.delete(len - 3, len);   // remove "abc"
            }
        }

        return stack.length() == 0;
    }
}