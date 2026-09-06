class Solution {
    public String lastSubstring(String s) {
        int n = s.length();
        int i = 0;          // best candidate start
        int j = 1;          // current candidate start
        int k = 0;          // common prefix length

        while (j + k < n) {
            if (s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            } else if (s.charAt(i + k) > s.charAt(j + k)) {
                // current best is still better
                j = j + k + 1;
                k = 0;
            } else {
                // candidate at j is better
                i = Math.max(i + k + 1, j);
                j = i + 1;
                k = 0;
            }
        }
        return s.substring(i);
    }
}