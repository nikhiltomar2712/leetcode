class Solution {
    public long minOperations(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        long pos = 0;   // total +k needed
        long neg = 0;   // total -k needed (absolute value)

        for (int i = 0; i < n; i++) {
            long diff = (long) nums2[i] - nums1[i];

            if (k == 0) {
                if (diff != 0) return -1;
                continue;
            }

            if (diff % k != 0) return -1;

            long steps = diff / k;
            if (steps > 0) {
                pos += steps;
            } else {
                neg -= steps;   // make it positive
            }
        }

        // The amount we need to “give” must equal the amount we need to “take”
        return pos == neg ? pos : -1;
    }
}