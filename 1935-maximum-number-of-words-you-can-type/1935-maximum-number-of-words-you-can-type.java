class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] broken = new boolean[26];
        for (char c : brokenLetters.toCharArray()) {
            broken[c - 'a'] = true;
        }
        int count = 0;
        for (String word : text.split(" ")) {
            boolean ok = true;
            for (char c : word.toCharArray()) {
                if (broken[c - 'a']) {
                    ok = false;
                    break;
                }
            }
            if (ok) count++;
        }
        return count;
    }
}