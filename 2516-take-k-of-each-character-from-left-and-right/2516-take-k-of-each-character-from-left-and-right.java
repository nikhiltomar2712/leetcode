class Solution {
    public int takeCharacters(String s, int k) {
        int n = s.length();
        int[] total = new int[3];
        for (char c : s.toCharArray()) total[c - 'a']++;
        for (int t : total) {
            if (t < k) return -1;
        }
        int[] window = new int[3];
        int left = 0, maxLen = 0;
        for (int right = 0; right < n; right++) {
            window[s.charAt(right) - 'a']++;
            while (window[0] > total[0] - k || window[1] > total[1] - k || window[2] > total[2] - k) {
                window[s.charAt(left) - 'a']--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return n - maxLen;
    }
}