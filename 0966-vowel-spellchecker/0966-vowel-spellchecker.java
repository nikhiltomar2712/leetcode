class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exact = new HashSet<>();
        Map<String, String> caseInsensitive = new HashMap<>();
        Map<String, String> vowelInsensitive = new HashMap<>();

        for (String word : wordlist) {
            exact.add(word);
            String lower = word.toLowerCase();
            caseInsensitive.putIfAbsent(lower, word);
            vowelInsensitive.putIfAbsent(devowel(lower), word);
        }

        String[] ans = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            if (exact.contains(q)) {
                ans[i] = q;                          // exact match (case-sensitive)
            } else {
                String lower = q.toLowerCase();
                if (caseInsensitive.containsKey(lower)) {
                    ans[i] = caseInsensitive.get(lower);  // case-insensitive match
                } else {
                    String pattern = devowel(lower);
                    ans[i] = vowelInsensitive.getOrDefault(pattern, "");  // vowel-error match
                }
            }
        }
        return ans;
    }

    private String devowel(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (isVowel(cs[i])) {
                cs[i] = '*';
            }
        }
        return new String(cs);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}