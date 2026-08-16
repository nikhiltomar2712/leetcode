class Solution {
    public String decodeAtIndex(String s, int k) {
        long size = 0;

        // 1. Calculate the total length of the decoded string
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                size *= c - '0';
            } else {
                size++;
            }
        }

        // 2. Walk backwards to find the k-th character
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            k %= size;                 // reduce k into the current size

            if (k == 0 && Character.isLetter(c)) {
                return String.valueOf(c);
            }

            if (Character.isDigit(c)) {
                size /= c - '0';
            } else {
                size--;
            }
        }

        return ""; // should never reach here
    }
}