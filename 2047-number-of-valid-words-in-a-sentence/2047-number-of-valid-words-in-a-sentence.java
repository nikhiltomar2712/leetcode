class Solution {
    public int countValidWords(String sentence) {
        int count = 0;
        for (String word : sentence.split("\\s+")) {
            if (word.isEmpty()) continue;
            if (isValid(word)) count++;
        }
        return count;
    }

    private boolean isValid(String word) {
        int hyphen = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isDigit(c)) return false;
            if (c == '-') {
                hyphen++;
                if (hyphen > 1) return false;
                if (i == 0 || i == word.length() - 1) return false;
                if (!Character.isLetter(word.charAt(i - 1)) || !Character.isLetter(word.charAt(i + 1))) return false;
            }
            if (c == '!' || c == '.' || c == ',') {
                if (i != word.length() - 1) return false;
            }
        }
        return true;
    }
}