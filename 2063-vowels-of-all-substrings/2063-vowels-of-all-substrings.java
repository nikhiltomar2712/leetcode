class Solution {
    public long countVowels(String word) {
        long res = 0;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                res += (long) (i + 1) * (n - i);
            }
        }
        return res;
    }
}