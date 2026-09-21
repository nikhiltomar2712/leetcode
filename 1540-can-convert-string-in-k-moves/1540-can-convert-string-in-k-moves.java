class Solution {
    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int diff = (t.charAt(i) - s.charAt(i) + 26) % 26;
            if (diff == 0) {
                continue;
            }
            count[diff]++;
            if (diff + 26 * (count[diff] - 1) > k) {
                return false;
            }
        }

        return true;
    }
}