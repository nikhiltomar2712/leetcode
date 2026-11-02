class Solution {
    private int[] parent;
    private long[] segSum;   // sum of the segment rooted at each index

    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        int n = nums.length;
        parent = new int[n];
        segSum = new long[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        long[] ans = new long[n];
        long maxSum = 0;

        // Process removals in reverse (from last removal to first)
        for (int j = n - 1; j > 0; j--) {
            int idx = removeQueries[j];
            segSum[idx] = nums[idx];   // activate this element

            // Merge with left neighbour if it is already active
            if (idx > 0 && segSum[find(idx - 1)] > 0) {
                union(idx, idx - 1);
            }
            // Merge with right neighbour if it is already active
            if (idx < n - 1 && segSum[find(idx + 1)] > 0) {
                union(idx, idx + 1);
            }

            // Update the maximum segment sum seen so far
            maxSum = Math.max(maxSum, segSum[find(idx)]);
            ans[j - 1] = maxSum;   // answer after the first j removals
        }

        return ans;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);   // path compression
        }
        return parent[x];
    }

    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return;
        parent[pa] = pb;          // attach pa under pb
        segSum[pb] += segSum[pa]; // combine segment sums
    }
}