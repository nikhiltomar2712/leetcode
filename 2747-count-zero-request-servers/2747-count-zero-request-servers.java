class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);
        int q = queries.length;
        int[][] qs = new int[q][2];
        for (int i = 0; i < q; i++) {
            qs[i][0] = queries[i];
            qs[i][1] = i;
        }
        Arrays.sort(qs, (a, b) -> a[0] - b[0]);
        int[] res = new int[q];
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0, right = 0;
        for (int[] query : qs) {
            int time = query[0], idx = query[1];
            while (right < logs.length && logs[right][1] <= time) {
                count.merge(logs[right][0], 1, Integer::sum);
                right++;
            }
            while (left < logs.length && logs[left][1] < time - x) {
                count.merge(logs[left][0], -1, Integer::sum);
                if (count.get(logs[left][0]) == 0) count.remove(logs[left][0]);
                left++;
            }
            res[idx] = n - count.size();
        }
        return res;
    }
}