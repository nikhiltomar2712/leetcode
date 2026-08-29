class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> pos = new HashMap<>();

        // Group indices by value
        for (int i = 0; i < n; i++) {
            pos.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Precompute min distance for every index
        int[] minDist = new int[n];
        Arrays.fill(minDist, -1);

        for (List<Integer> indices : pos.values()) {
            int m = indices.size();
            if (m == 1) continue; // no other occurrence

            for (int i = 0; i < m; i++) {
                int curr = indices.get(i);
                int prev = indices.get((i - 1 + m) % m);
                int next = indices.get((i + 1) % m);

                // Circular distances
                int distPrev = Math.min(Math.abs(curr - prev), n - Math.abs(curr - prev));
                int distNext = Math.min(Math.abs(curr - next), n - Math.abs(curr - next));

                minDist[curr] = Math.min(distPrev, distNext);
            }
        }

        // Answer the queries
        List<Integer> answer = new ArrayList<>(queries.length);
        for (int q : queries) {
            answer.add(minDist[q]);
        }
        return answer;
    }
}