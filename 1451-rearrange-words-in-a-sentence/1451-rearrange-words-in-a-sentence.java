class Solution {
    public String arrangeWords(String text) {
        String[] words = text.split(" ");
        words[0] = words[0].toLowerCase();

        Arrays.sort(words, (a, b) -> a.length() - b.length());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(words[i]);
        }

        String result = sb.toString();
        return Character.toUpperCase(result.charAt(0)) + result.substring(1);
    }
}