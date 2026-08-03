class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : answers) {
            cnt.merge(x, 1, Integer::sum);
        }

        int ans = 0;
        for (var e : cnt.entrySet()) {
            int x = e.getKey();
            int v = e.getValue();
            int group = x + 1;                     // size of one color group
            // number of groups needed = ceil(v / group)
            ans += (v + group - 1) / group * group;
        }
        return ans;
    }
}