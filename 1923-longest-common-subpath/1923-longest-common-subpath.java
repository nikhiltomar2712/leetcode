class Solution {
    public int longestCommonSubpath(int n, int[][] paths) {
        int left = 0, right = Integer.MAX_VALUE;
        for (int[] path : paths) right = Math.min(right, path.length);
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (check(paths, mid, n)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int[][] paths, int len, int n) {
        long mod = (1L << 61) - 1;
        long base = n + 1;
        Map<Long, Integer> count = new HashMap<>();
        long pow = 1;
        for (int i = 0; i < len; i++) pow = mul(pow, base, mod);
        for (int[] path : paths) {
            if (path.length < len) return false;
            long hash = 0;
            Set<Long> seen = new HashSet<>();
            for (int i = 0; i < path.length; i++) {
                hash = (mul(hash, base, mod) + path[i] + 1) % mod;
                if (i >= len) {
                    hash = (hash - mul(path[i - len] + 1, pow, mod) % mod + mod) % mod;
                }
                if (i >= len - 1) {
                    seen.add(hash);
                }
            }
            for (long h : seen) {
                int c = count.getOrDefault(h, 0) + 1;
                if (c == paths.length) return true;
                count.put(h, c);
            }
        }
        return false;
    }

    private long mul(long a, long b, long mod) {
        long res = 0;
        a %= mod;
        while (b > 0) {
            if ((b & 1) == 1) res = (res + a) % mod;
            a = (a * 2) % mod;
            b >>= 1;
        }
        return res;
    }
}