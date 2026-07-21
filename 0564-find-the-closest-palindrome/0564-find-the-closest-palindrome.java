class Solution {
    public String nearestPalindromic(String n) {
        long N = Long.parseLong(n);
        int L = n.length();
        long cand1 = (long) Math.pow(10, L - 1) - 1;
        long cand2 = (long) Math.pow(10, L) + 1;
        long prefix = Long.parseLong(n.substring(0, (L + 1) / 2));
        long[] palCands = new long[3];
        for (int i = 0; i < 3; i++) {
            long p = prefix + i - 1;
            palCands[i] = createPal(p, L % 2 == 0);
        }
        long minDiff = Long.MAX_VALUE;
        long res = Long.MAX_VALUE;
        long[] allCands = {cand1, cand2, palCands[0], palCands[1], palCands[2]};
        for (long c : allCands) {
            if (c == N || c < 0) continue;
            long diff = Math.abs(c - N);
            if (diff < minDiff || (diff == minDiff && c < res)) {
                minDiff = diff;
                res = c;
            }
        }
        return String.valueOf(res);
    }

    private long createPal(long p, boolean even) {
        if (p < 0) return -1;
        long pal = p;
        long temp = p;
        if (!even) {
            temp /= 10;
        }
        while (temp > 0) {
            pal = pal * 10 + (temp % 10);
            temp /= 10;
        }
        return pal;
    }
}