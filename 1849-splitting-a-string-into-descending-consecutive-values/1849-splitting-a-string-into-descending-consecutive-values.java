class Solution {
    public boolean splitString(String s) {
        return dfs(s, 0, -1, 0);
    }

    private boolean dfs(String s, int start, long prev, int count) {
        if (start == s.length()) {
            return count >= 2;
        }

        long curr = 0;
        for (int i = start; i < s.length(); i++) {
            curr = curr * 10 + (s.charAt(i) - '0');

            // Safety guard against overflow / unnecessarily large numbers
            if (curr > 10_000_000_000L) {
                break;
            }

            // First number can be anything; later numbers must equal prev - 1
            if ((prev == -1 || curr == prev - 1) && dfs(s, i + 1, curr, count + 1)) {
                return true;
            }
        }
        return false;
    }
}