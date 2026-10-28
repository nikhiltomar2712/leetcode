class Solution {
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        boolean[] used = new boolean[2001];
        int res = 0;
        for (int[] task : tasks) {
            int start = task[0], end = task[1], duration = task[2];
            for (int t = start; t <= end; t++) {
                if (used[t]) duration--;
            }
            for (int t = end; t >= start && duration > 0; t--) {
                if (!used[t]) {
                    used[t] = true;
                    duration--;
                    res++;
                }
            }
        }
        return res;
    }
}