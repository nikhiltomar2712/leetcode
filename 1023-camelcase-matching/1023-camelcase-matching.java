class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for (String query : queries) {
            result.add(isMatch(query, pattern));
        }
        return result;
    }

    private boolean isMatch(String query, String pattern) {
        int j = 0; // pointer for pattern
        for (char c : query.toCharArray()) {
            if (j < pattern.length() && c == pattern.charAt(j)) {
                j++; // matched a character from the pattern
            } else if (Character.isUpperCase(c)) {
                return false; // extra uppercase letter not in pattern
            }
            // lowercase letters are freely allowed (skipped)
        }
        return j == pattern.length(); // must consume the whole pattern
    }
}