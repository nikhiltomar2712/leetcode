class Solution {
    public int[] numberOfLines(int[] widths, String s) {
        int lines = 1;      // At least one line is needed
        int currentWidth = 0;

        for (char c : s.toCharArray()) {
            int charWidth = widths[c - 'a']; // Get width for this letter

            // If adding this character exceeds the line limit
            if (currentWidth + charWidth > 100) {
                lines++;                // Start a new line
                currentWidth = charWidth; // Put this character on the new line
            } else {
                currentWidth += charWidth; // Add to current line
            }
        }

        return new int[]{lines, currentWidth};
    }
}