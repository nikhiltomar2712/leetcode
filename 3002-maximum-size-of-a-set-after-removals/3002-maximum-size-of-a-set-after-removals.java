class Solution {
    public int maximumSetSize(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        for (int n : nums1) s1.add(n);
        for (int n : nums2) s2.add(n);

        int common = 0;
        for (int n : s1) {
            if (s2.contains(n)) common++;
        }

        int only1 = s1.size() - common;
        int only2 = s2.size() - common;
        int half = nums1.length / 2;

        int take1 = Math.min(only1, half);
        int take2 = Math.min(only2, half);

        int remaining = 2 * half - take1 - take2;
        int takeCommon = Math.min(common, remaining);

        return take1 + take2 + takeCommon;
    }
}