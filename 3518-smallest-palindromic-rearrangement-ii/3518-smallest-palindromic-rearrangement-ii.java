class Solution {
    private static final long MAX = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        char mid = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                mid = (char) ('a' + i);
                freq[i]--;
                break;
            }
        }

        int[] half = new int[26];
        int halfLen = 0;
        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
        }

        if (k > multinomial(half)) {
            return "";
        }

        StringBuilder left = new StringBuilder();
        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;
                half[c]--;
                long ways = multinomial(half);
                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= (int) ways;
                    half[c]++;
                }
            }
        }

        String first = left.toString();
        String second = left.reverse().toString();
        if (mid == 0) {
            return first + second;
        }
        return first + mid + second;
    }

    private long multinomial(int[] counts) {
        int total = 0;
        for (int c : counts) total += c;
        long res = 1;
        for (int i = 0; i < 26; i++) {
            int cnt = counts[i];
            res = res * binom(total, cnt);
            if (res >= MAX) return MAX;
            total -= cnt;
        }
        return res;
    }

    private long binom(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k > n - k) k = n - k;
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
            if (res >= MAX) return MAX;
        }
        return res;
    }
}