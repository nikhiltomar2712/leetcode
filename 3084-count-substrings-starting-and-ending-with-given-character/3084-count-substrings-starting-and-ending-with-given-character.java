class Solution {
    public long countSubstrings(String s, char c) {
        long count = 0;

        for (char ch : s.toCharArray()) {
            if (ch == c) {
                count++;
            }
        }

        return count * (count + 1) / 2;
    }
}