class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (isVowel(words[i]) ? 1 : 0);
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = prefix[queries[i][1] + 1] - prefix[queries[i][0]];
        }
        return res;
    }

    private boolean isVowel(String w) {
        char first = w.charAt(0), last = w.charAt(w.length() - 1);
        return "aeiou".indexOf(first) != -1 && "aeiou".indexOf(last) != -1;
    }
}