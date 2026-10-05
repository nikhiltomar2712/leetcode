class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] prefix = new int[n + 1];
        int[] leftCandle = new int[n];
        int[] rightCandle = new int[n];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (s.charAt(i) == '*' ? 1 : 0);
        }
        int last = -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '|') last = i;
            leftCandle[i] = last;
        }
        last = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '|') last = i;
            rightCandle[i] = last;
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = rightCandle[queries[i][0]];
            int r = leftCandle[queries[i][1]];
            if (l != -1 && r != -1 && l < r) {
                res[i] = prefix[r] - prefix[l];
            }
        }
        return res;
    }
}