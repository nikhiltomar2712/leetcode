class Solution {
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;

        // XOR of all numbers from 1 to n
        int totalXor = 0;
        for (int i = 1; i <= n; i++) {
            totalXor ^= i;
        }

        // XOR of encoded[1], encoded[3], encoded[5], ... gives perm[0]
        int firstXor = 0;
        for (int i = 1; i < encoded.length; i += 2) {
            firstXor ^= encoded[i];
        }

        int[] perm = new int[n];
        perm[0] = totalXor ^ firstXor;

        for (int i = 0; i < encoded.length; i++) {
            perm[i + 1] = perm[i] ^ encoded[i];
        }

        return perm;
    }
}