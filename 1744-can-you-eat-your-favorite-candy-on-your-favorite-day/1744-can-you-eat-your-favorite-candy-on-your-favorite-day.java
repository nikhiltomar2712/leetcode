class Solution {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + candiesCount[i];
        }

        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int type = queries[i][0];
            int day = queries[i][1];
            int cap = queries[i][2];

            // Minimum candies eaten before 'day' (eating 1 per day)
            long minEaten = day;
            // Maximum candies eaten through 'day' (eating cap per day)
            long maxEaten = (long) (day + 1) * cap;

            // Candies of types before 'type' occupy [0, prefix[type])
            long beforeStart = prefix[type];
            long beforeEnd = prefix[type + 1]; // exclusive

            // We must eat at least one candy of 'type' on 'day'
            // So maxEaten must reach past beforeStart, and minEaten must be before beforeEnd
            ans[i] = maxEaten > beforeStart && minEaten < beforeEnd;
        }

        return ans;
    }
}