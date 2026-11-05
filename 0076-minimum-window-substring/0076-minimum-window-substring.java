class Solution {
    public String minWindow(String s, String t) {
        int[] need = new int[128];
        int[] window = new int[128];
        for (char c : t.toCharArray()) {
            need[c]++;
        }
        int left = 0, right = 0;
        int valid = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need[c] > 0) {
                window[c]++;
                if (window[c] <= need[c]) valid++;
            }
            while (valid == t.length()) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                char d = s.charAt(left);
                left++;
                if (need[d] > 0) {
                    if (window[d] <= need[d]) valid--;
                    window[d]--;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}