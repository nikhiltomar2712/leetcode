class Solution {
    public String reorderSpaces(String text) {
        // Extract words (ignore empty tokens from multiple spaces)
        String[] parts = text.split(" ");
        java.util.List<String> words = new java.util.ArrayList<>();
        int totalSpaces = 0;

        for (String p : parts) {
            if (p.isEmpty()) {
                totalSpaces++;
            } else {
                words.add(p);
            }
        }
        // Note: split(" ") on "a  b" gives ["a","","b"] — one empty string per extra space
        // But we need to count ALL spaces; safer to count directly.

        // Recount spaces reliably
        totalSpaces = 0;
        for (char c : text.toCharArray()) {
            if (c == ' ') totalSpaces++;
        }

        int n = words.size();
        if (n == 1) {
            StringBuilder sb = new StringBuilder(words.get(0));
            for (int i = 0; i < totalSpaces; i++) sb.append(' ');
            return sb.toString();
        }

        int spacesBetween = totalSpaces / (n - 1);
        int extra = totalSpaces % (n - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(words.get(i));
            if (i < n - 1) {
                for (int s = 0; s < spacesBetween; s++) sb.append(' ');
            }
        }
        for (int s = 0; s < extra; s++) sb.append(' ');

        return sb.toString();
    }
}