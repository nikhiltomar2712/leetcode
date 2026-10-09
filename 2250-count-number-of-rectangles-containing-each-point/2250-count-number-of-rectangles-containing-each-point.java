class Solution {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        List<Integer>[] heights = new List[101];
        for (int i = 0; i <= 100; i++) heights[i] = new ArrayList<>();
        for (int[] r : rectangles) {
            heights[r[1]].add(r[0]);
        }
        for (int i = 1; i <= 100; i++) {
            Collections.sort(heights[i]);
        }
        int[] res = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0], y = points[i][1];
            int count = 0;
            for (int h = y; h <= 100; h++) {
                List<Integer> list = heights[h];
                int lo = 0, hi = list.size();
                while (lo < hi) {
                    int mid = (lo + hi) / 2;
                    if (list.get(mid) >= x) hi = mid;
                    else lo = mid + 1;
                }
                count += list.size() - lo;
            }
            res[i] = count;
        }
        return res;
    }
}