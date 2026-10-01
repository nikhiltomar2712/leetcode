class Solution {
    public List<List<Long>> splitPainting(int[][] segments) {
        TreeMap<Integer, Long> map = new TreeMap<>();
        for (int[] seg : segments) {
            map.merge(seg[0], (long) seg[2], Long::sum);
            map.merge(seg[1], (long) -seg[2], Long::sum);
        }
        List<List<Long>> res = new ArrayList<>();
        long cur = 0;
        int prev = -1;
        for (Map.Entry<Integer, Long> e : map.entrySet()) {
            int pos = e.getKey();
            if (cur > 0 && prev != -1) {
                res.add(Arrays.asList((long) prev, (long) pos, cur));
            }
            cur += e.getValue();
            prev = pos;
        }
        return res;
    }
}