class Solution {
    public int minimumSwap(String s1, String s2) {
        int xy = 0; // s1 has 'x', s2 has 'y'
        int yx = 0; // s1 has 'y', s2 has 'x'

        for (int i = 0; i < s1.length(); i++) {
            char a = s1.charAt(i);
            char b = s2.charAt(i);
            if (a == b) continue;

            if (a == 'x') xy++;
            else yx++;
        }

        // Total mismatches must be even
        if ((xy + yx) % 2 == 1) return -1;

        // Each pair of same-type mismatches costs 1 swap
        // One leftover xy + one leftover yx costs 2 swaps
        return xy / 2 + yx / 2 + xy % 2 + yx % 2;
    }
}