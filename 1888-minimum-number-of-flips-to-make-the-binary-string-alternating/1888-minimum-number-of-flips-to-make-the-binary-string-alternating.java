class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String ss = s + s;
        int diff1 = 0, diff2 = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 2 * n; i++) {
            char c = ss.charAt(i);
            char e1 = (i % 2 == 0) ? '0' : '1';
            char e2 = (i % 2 == 0) ? '1' : '0';
            if (c != e1) diff1++;
            if (c != e2) diff2++;
            if (i >= n) {
                char old = ss.charAt(i - n);
                char o1 = ((i - n) % 2 == 0) ? '0' : '1';
                char o2 = ((i - n) % 2 == 0) ? '1' : '0';
                if (old != o1) diff1--;
                if (old != o2) diff2--;
            }
            if (i >= n - 1) {
                res = Math.min(res, Math.min(diff1, diff2));
            }
        }
        return res;
    }
}