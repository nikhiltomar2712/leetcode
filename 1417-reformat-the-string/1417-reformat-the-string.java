class Solution {
    public String reformat(String s) {
        StringBuilder letters = new StringBuilder();
        StringBuilder digits = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                letters.append(c);
            } else {
                digits.append(c);
            }
        }

        int lCount = letters.length();
        int dCount = digits.length();

        // Impossible if counts differ by more than 1
        if (Math.abs(lCount - dCount) > 1) {
            return "";
        }

        // Decide which type goes first (the more frequent one)
        StringBuilder first = lCount >= dCount ? letters : digits;
        StringBuilder second = lCount >= dCount ? digits : letters;

        StringBuilder result = new StringBuilder();
        int i = 0, j = 0;
        while (i < first.length() || j < second.length()) {
            if (i < first.length()) {
                result.append(first.charAt(i++));
            }
            if (j < second.length()) {
                result.append(second.charAt(j++));
            }
        }

        return result.toString();
    }
}