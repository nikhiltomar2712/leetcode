class Solution {
    public int scoreOfStudents(String s, int[] answers) {
        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) nums.add(c - '0');
            else ops.add(c);
        }
        int m = nums.size();
        Set<Integer>[][] dp = new Set[m][m];
        for (int i = 0; i < m; i++) {
            dp[i][i] = new HashSet<>();
            dp[i][i].add(nums.get(i));
        }
        for (int len = 2; len <= m; len++) {
            for (int i = 0; i + len <= m; i++) {
                int j = i + len - 1;
                dp[i][j] = new HashSet<>();
                for (int k = i; k < j; k++) {
                    for (int a : dp[i][k]) {
                        for (int b : dp[k + 1][j]) {
                            int val = ops.get(k) == '+' ? a + b : a * b;
                            if (val <= 1000) dp[i][j].add(val);
                        }
                    }
                }
            }
        }
        int correct = correctEval(nums, ops);
        Set<Integer> possible = dp[0][m - 1];
        int score = 0;
        for (int ans : answers) {
            if (ans == correct) score += 5;
            else if (possible.contains(ans)) score += 2;
        }
        return score;
    }
    
    private int correctEval(List<Integer> nums, List<Character> ops) {
        List<Integer> terms = new ArrayList<>();
        terms.add(nums.get(0));
        for (int i = 0; i < ops.size(); i++) {
            if (ops.get(i) == '*') {
                terms.set(terms.size() - 1, terms.get(terms.size() - 1) * nums.get(i + 1));
            } else {
                terms.add(nums.get(i + 1));
            }
        }
        int res = 0;
        for (int t : terms) res += t;
        return res;
    }
}