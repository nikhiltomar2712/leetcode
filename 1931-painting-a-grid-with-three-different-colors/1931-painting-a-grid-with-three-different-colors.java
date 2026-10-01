class Solution {
    public int colorTheGrid(int m, int n) {
        int mod = 1000000007;
        List<Integer> states = new ArrayList<>();
        for (int mask = 0; mask < (int) Math.pow(3, m); mask++) {
            if (valid(mask, m)) states.add(mask);
        }
        int size = states.size();
        List<Integer>[] next = new List[size];
        for (int i = 0; i < size; i++) {
            next[i] = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (compatible(states.get(i), states.get(j), m)) {
                    next[i].add(j);
                }
            }
        }
        int[] dp = new int[size];
        Arrays.fill(dp, 1);
        for (int col = 1; col < n; col++) {
            int[] ndp = new int[size];
            for (int i = 0; i < size; i++) {
                for (int j : next[i]) {
                    ndp[i] = (ndp[i] + dp[j]) % mod;
                }
            }
            dp = ndp;
        }
        int res = 0;
        for (int v : dp) res = (res + v) % mod;
        return res;
    }

    private boolean valid(int mask, int m) {
        int prev = -1;
        for (int i = 0; i < m; i++) {
            int cur = mask % 3;
            mask /= 3;
            if (cur == prev) return false;
            prev = cur;
        }
        return true;
    }

    private boolean compatible(int a, int b, int m) {
        for (int i = 0; i < m; i++) {
            if (a % 3 == b % 3) return false;
            a /= 3;
            b /= 3;
        }
        return true;
    }
}