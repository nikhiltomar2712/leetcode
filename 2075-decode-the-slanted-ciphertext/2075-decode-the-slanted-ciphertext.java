class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1) return encodedText;
        int n = encodedText.length();
        int cols = n / rows;
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows && c + r < cols; r++) {
                sb.append(encodedText.charAt(r * cols + c + r));
            }
        }
        int end = sb.length();
        while (end > 0 && sb.charAt(end - 1) == ' ') {
            end--;
        }
        return sb.substring(0, end);
    }
}