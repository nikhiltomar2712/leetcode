class Solution {
    public int numSteps(String s) {
        StringBuilder sb = new StringBuilder(s);
        int steps = 0;
        
        while (sb.length() > 1) {
            char last = sb.charAt(sb.length() - 1);
            
            if (last == '0') {
                // Even: divide by 2 -> drop last char
                sb.deleteCharAt(sb.length() - 1);
                steps++;
            } else {
                // Odd: add 1 -> handle carry
                int i = sb.length() - 1;
                // Flip trailing 1s to 0s
                while (i >= 0 && sb.charAt(i) == '1') {
                    sb.setCharAt(i, '0');
                    i--;
                }
                if (i < 0) {
                    // All bits were '1', so we get "1" followed by all '0's
                    // e.g., "111" + 1 = "1000"
                    sb.insert(0, '1');
                } else {
                    sb.setCharAt(i, '1');
                }
                steps++;
            }
        }
        
        return steps;
    }
}