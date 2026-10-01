class Solution {
    public int addRungs(int[] rungs, int dist) {
        int res = 0, prev = 0;
        for (int rung : rungs) {
            int gap = rung - prev;
            if (gap > dist) {
                res += (gap - 1) / dist;
            }
            prev = rung;
        }
        return res;
    }
}