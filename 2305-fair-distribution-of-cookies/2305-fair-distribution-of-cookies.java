class Solution {
    int res = Integer.MAX_VALUE;

    public int distributeCookies(int[] cookies, int k) {
        int[] children = new int[k];
        dfs(cookies, 0, children, k);
        return res;
    }

    private void dfs(int[] cookies, int idx, int[] children, int k) {
        if (idx == cookies.length) {
            int max = 0;
            for (int c : children) max = Math.max(max, c);
            res = Math.min(res, max);
            return;
        }
        for (int i = 0; i < k; i++) {
            children[i] += cookies[idx];
            dfs(cookies, idx + 1, children, k);
            children[i] -= cookies[idx];
            if (children[i] == 0) break;
        }
    }
}