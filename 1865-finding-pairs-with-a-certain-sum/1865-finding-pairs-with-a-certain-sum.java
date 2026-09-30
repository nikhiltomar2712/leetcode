class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> map2;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.map2 = new HashMap<>();
        for (int num : nums2) {
            map2.merge(num, 1, Integer::sum);
        }
    }

    public void add(int index, int val) {
        int old = nums2[index];
        map2.merge(old, -1, Integer::sum);
        if (map2.get(old) == 0) map2.remove(old);
        nums2[index] += val;
        map2.merge(nums2[index], 1, Integer::sum);
    }

    public int count(int tot) {
        int res = 0;
        for (int num : nums1) {
            res += map2.getOrDefault(tot - num, 0);
        }
        return res;
    }
}