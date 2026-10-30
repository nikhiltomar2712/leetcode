class Solution {
    public int[] circularGameLosers(int n, int k) {
        boolean[] visited = new boolean[n];
        int cur = 0;
        int step = 1;
        while (!visited[cur]) {
            visited[cur] = true;
            cur = (cur + step * k) % n;
            step++;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) res.add(i + 1);
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }
}