class Solution {
    public int longestBeautifulSubstring(String word) {
        int res = 0, count = 1, len = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) >= word.charAt(i - 1)) {
                len++;
                if (word.charAt(i) > word.charAt(i - 1)) count++;
            } else {
                count = 1;
                len = 1;
            }
            if (count == 5) res = Math.max(res, len);
        }
        return res;
    }
}