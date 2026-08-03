class Solution {
    private List<String> ans = new ArrayList<>();
    private char[] chars;

    public List<String> letterCasePermutation(String s) {
        chars = s.toCharArray();
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == chars.length) {
            ans.add(new String(chars));
            return;
        }

        // Always explore the current character as-is
        dfs(i + 1);

        // If it's a letter, also explore the flipped case
        if (Character.isLetter(chars[i])) {
            chars[i] ^= 32;          // toggle case (ASCII trick)
            dfs(i + 1);
            chars[i] ^= 32;          // backtrack
        }
    }
}