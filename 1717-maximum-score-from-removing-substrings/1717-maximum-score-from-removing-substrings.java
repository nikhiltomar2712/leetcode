class Solution {
    public int maximumGain(String s, int x, int y) {
        String high = x >= y ? "ab" : "ba";
        String low = x >= y ? "ba" : "ab";
        int highScore = Math.max(x, y);
        int lowScore = Math.min(x, y);

        int total = 0;
        StringBuilder sb = new StringBuilder();

        // Remove all high-value pairs first
        for (char c : s.toCharArray()) {
            int len = sb.length();
            if (len > 0 && sb.charAt(len - 1) == high.charAt(0) && c == high.charAt(1)) {
                sb.deleteCharAt(len - 1);
                total += highScore;
            } else {
                sb.append(c);
            }
        }

        // Remove all low-value pairs from remaining string
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            int len = sb2.length();
            if (len > 0 && sb2.charAt(len - 1) == low.charAt(0) && c == low.charAt(1)) {
                sb2.deleteCharAt(len - 1);
                total += lowScore;
            } else {
                sb2.append(c);
            }
        }

        return total;
    }
}