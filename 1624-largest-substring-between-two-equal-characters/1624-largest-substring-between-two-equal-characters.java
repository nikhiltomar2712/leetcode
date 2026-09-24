class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] firstIndex = new int[26];
        java.util.Arrays.fill(firstIndex, -1);
        int maxLen = -1;

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (firstIndex[c] == -1) {
                firstIndex[c] = i;
            } else {
                maxLen = Math.max(maxLen, i - firstIndex[c] - 1);
            }
        }

        return maxLen;
    }
}