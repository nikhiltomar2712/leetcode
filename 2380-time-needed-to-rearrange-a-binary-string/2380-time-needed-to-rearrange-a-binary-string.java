class Solution {
    public int secondsToRemoveOccurrences(String s) {
        int res = 0, zeros = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                zeros++;
            } else if (zeros > 0) {
                res = Math.max(res + 1, zeros);
            }
        }
        return res;
    }
}