class Solution {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int n = rooms.length, q = queries.length;
        Arrays.sort(rooms, (a, b) -> b[1] - a[1]);
        int[][] qs = new int[q][3];
        for (int i = 0; i < q; i++) {
            qs[i] = new int[]{queries[i][0], queries[i][1], i};
        }
        Arrays.sort(qs, (a, b) -> b[1] - a[1]);
        TreeSet<Integer> set = new TreeSet<>();
        int[] res = new int[q];
        int idx = 0;
        for (int i = 0; i < q; i++) {
            int pref = qs[i][0], minSize = qs[i][1], qi = qs[i][2];
            while (idx < n && rooms[idx][1] >= minSize) {
                set.add(rooms[idx][0]);
                idx++;
            }
            Integer floor = set.floor(pref);
            Integer ceil = set.ceiling(pref);
            if (floor == null && ceil == null) {
                res[qi] = -1;
            } else if (floor == null) {
                res[qi] = ceil;
            } else if (ceil == null) {
                res[qi] = floor;
            } else {
                res[qi] = (pref - floor <= ceil - pref) ? floor : ceil;
            }
        }
        return res;
    }
}