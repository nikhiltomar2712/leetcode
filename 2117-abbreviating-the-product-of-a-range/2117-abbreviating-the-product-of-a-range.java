class Solution {
    public String abbreviateProduct(int left, int right) {
        int cnt2 = 0, cnt5 = 0;

        // Count total factors of 2 and 5 in the range
        for (int i = left; i <= right; i++) {
            int x = i;
            while (x % 2 == 0) {
                cnt2++;
                x /= 2;
            }
            while (x % 5 == 0) {
                cnt5++;
                x /= 5;
            }
        }

        int c = Math.min(cnt2, cnt5); // Number of trailing zeros
        cnt2 = cnt5 = c;

        long suf = 1;
        double pre = 1.0;
        boolean moreThan10Digits = false;

        for (int i = left; i <= right; i++) {
            // Update suffix and remove remaining 2s and 5s
            suf *= i;
            while (cnt2 > 0 && suf % 2 == 0) {
                suf /= 2;
                cnt2--;
            }
            while (cnt5 > 0 && suf % 5 == 0) {
                suf /= 5;
                cnt5--;
            }

            // Keep only last 10 digits of suffix
            if (suf >= 10_000_000_000L) {
                moreThan10Digits = true;
                suf %= 10_000_000_000L;
            }

            // Update prefix (keep it roughly between 1 and 1e5)
            pre *= i;
            while (pre >= 1e5) {
                pre /= 10;
            }
        }

        if (moreThan10Digits) {
            // Abbreviate: first 5 digits + "..." + last 5 digits + "eC"
            String prefix = String.valueOf((int) pre);
            String suffix = String.format("%05d", suf % 100_000);
            return prefix + "..." + suffix + "e" + c;
        } else {
            // No abbreviation needed
            return suf + "e" + c;
        }
    }
}