class Solution {
    public long maxProduct(String s) {
        int n = s.length();
        int[] radius = manacher(s);          // radius[i] = half-length of longest odd palindrome centered at i
                                             // actual length = 2 * radius[i] + 1

        // left[i]  = max odd-palindrome length that ends at or before i
        // right[i] = max odd-palindrome length that starts at or after i
        long[] left  = new long[n];
        long[] right = new long[n];

        // build left[]
        int maxLen = 1;
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            // try to extend the previous maximum
            while (i - maxLen - 1 >= 0 &&
                   radius[(i - maxLen - 1 + i) / 2] * 2 + 1 > maxLen) {
                maxLen += 2;
            }
            left[i] = maxLen;
        }

        // build right[]
        maxLen = 1;
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            while (i + maxLen + 1 < n &&
                   radius[(i + maxLen + 1 + i) / 2] * 2 + 1 > maxLen) {
                maxLen += 2;
            }
            right[i] = maxLen;
        }

        long ans = 1;
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, left[i - 1] * right[i]);
        }
        return ans;
    }

    // classic Manacher for odd-length palindromes only
    private int[] manacher(String s) {
        int n = s.length();
        int[] p = new int[n];
        int center = 0, right = 0;

        for (int i = 0; i < n; i++) {
            int mirror = 2 * center - i;
            if (i < right) {
                p[i] = Math.min(right - i, p[mirror]);
            }
            // expand
            while (i - p[i] - 1 >= 0 && i + p[i] + 1 < n &&
                   s.charAt(i - p[i] - 1) == s.charAt(i + p[i] + 1)) {
                p[i]++;
            }
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }
        return p;
    }
}