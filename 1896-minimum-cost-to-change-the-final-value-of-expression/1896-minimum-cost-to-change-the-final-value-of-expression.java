class Solution {
    public int minOperationsToFlip(String expression) {
        // each frame: [cost0, cost1, operator]
        // operator is stored only while waiting for the right operand
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, 0, 0});          // dummy root frame

        for (char c : expression.toCharArray()) {
            if (c == '(') {
                stack.push(new int[]{0, 0, 0});  // new frame
            } else if (c == '&' || c == '|') {
                stack.peek()[2] = c;             // record operator
            } else {
                // digit or ')'
                if (Character.isDigit(c)) {
                    // leaf
                    int cost0 = (c == '0') ? 0 : 1;
                    int cost1 = (c == '1') ? 0 : 1;
                    stack.push(new int[]{cost0, cost1, 0});
                }

                // now combine with the left operand that is waiting on the stack
                int[] right = stack.pop();
                int[] left  = stack.pop();
                char op     = (char) left[2];

                if (op == '&') {
                    int c0 = Math.min(left[0], right[0]);
                    int c1 = Math.min(left[1] + right[1],
                                      Math.min(left[1], right[1]) + 1);
                    stack.push(new int[]{c0, c1, 0});
                } else if (op == '|') {
                    int c0 = Math.min(left[0] + right[0],
                                      Math.min(left[0], right[0]) + 1);
                    int c1 = Math.min(left[1], right[1]);
                    stack.push(new int[]{c0, c1, 0});
                } else {
                    // just a parenthesised expression – push the right value back
                    stack.push(right);
                }
            }
        }

        int[] root = stack.peek();
        return Math.max(root[0], root[1]);
    }
}