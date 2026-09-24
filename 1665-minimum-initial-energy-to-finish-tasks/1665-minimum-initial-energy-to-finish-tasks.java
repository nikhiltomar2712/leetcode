class Solution {
    public int minimumEffort(int[][] tasks) {
        // Sort by (minimum - actual) descending
        Arrays.sort(tasks, (t1, t2) ->
            (t2[1] - t2[0]) - (t1[1] - t1[0])
        );

        int ans = 0;
        int energy = 0;

        for (int[] task : tasks) {
            int actual = task[0];
            int minimum = task[1];

            if (energy < minimum) {
                ans += minimum - energy;
                energy = minimum;
            }
            energy -= actual;
        }

        return ans;
    }
}