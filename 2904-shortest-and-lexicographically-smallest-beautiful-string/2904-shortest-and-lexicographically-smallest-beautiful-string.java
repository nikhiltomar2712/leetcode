class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int bestLeft = -1;
        int minLength = s.length() + 1;
        int ones = 0;

        for (int l = 0, r = 0; r < s.length(); ++r) {
            if (s.charAt(r) == '1') {
                ++ones;
            }
            while (ones == k) {
                int len = r - l + 1;
                if (len < minLength) {
                    bestLeft = l;
                    minLength = len;
                } else if (len == minLength &&
                           s.substring(l, l + minLength)
                            .compareTo(s.substring(bestLeft, bestLeft + minLength)) < 0) {
                    bestLeft = l;
                }
                if (s.charAt(l++) == '1') {
                    --ones;
                }
            }
        }

        return bestLeft == -1 ? "" : s.substring(bestLeft, bestLeft + minLength);
    }
}