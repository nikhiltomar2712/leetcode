class Solution {
    public boolean areOccurrencesEqual(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int freq = 0;
        for (int c : count) {
            if (c == 0) continue;
            if (freq == 0) freq = c;
            else if (c != freq) return false;
        }
        return true;
    }
}