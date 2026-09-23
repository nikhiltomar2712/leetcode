import java.util.*;

class Solution {
    public int numTriplets(int[] nums1, int[] nums2) {
        return count(nums1, nums2) + count(nums2, nums1);
    }

    private int count(int[] a, int[] b) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int x : b) {
            freq.merge((long) x, 1, Integer::sum);
        }

        long result = 0;
        for (int v : a) {
            long target = (long) v * v;
            for (Map.Entry<Long, Integer> e : freq.entrySet()) {
                long x = e.getKey();
                int cx = e.getValue();

                if (x == 0) {
                    if (target == 0) {
                        result += (long) cx * (cx - 1) / 2;
                    }
                    continue;
                }
                if (target % x != 0) continue;
                long y = target / x;
                if (y < x) continue;        // count each unordered pair once
                if (y == x) {
                    result += (long) cx * (cx - 1) / 2;
                } else if (freq.containsKey(y)) {
                    result += (long) cx * freq.get(y);
                }
            }
        }
        return (int) result;
    }
}