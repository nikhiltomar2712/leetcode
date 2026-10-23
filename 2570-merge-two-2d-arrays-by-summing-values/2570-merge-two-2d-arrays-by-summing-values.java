class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] pair : nums1) {
            map.merge(pair[0], pair[1], Integer::sum);
        }
        for (int[] pair : nums2) {
            map.merge(pair[0], pair[1], Integer::sum);
        }
        int[][] res = new int[map.size()][2];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res[idx][0] = entry.getKey();
            res[idx][1] = entry.getValue();
            idx++;
        }
        return res;
    }
}