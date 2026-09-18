class Solution {
    public boolean canArrange(int[] arr, int k) {
        int[] count = new int[k];

        for (int num : arr) {
            int r = ((num % k) + k) % k;  // normalize negative remainders
            count[r]++;
        }

        // Remainder 0 must have even count
        if (count[0] % 2 != 0) return false;

        // For each r from 1 to k-1, count[r] must equal count[k-r]
        for (int r = 1; r <= k / 2; r++) {
            if (r == k - r) {
                // k is even, r == k/2: must pair among themselves
                if (count[r] % 2 != 0) return false;
            } else {
                if (count[r] != count[k - r]) return false;
            }
        }

        return true;
    }
}