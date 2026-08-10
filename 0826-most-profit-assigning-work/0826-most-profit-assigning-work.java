class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int m = worker.length;
        
        // Create array of jobs and sort by difficulty
        int[][] jobs = new int[n][2];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = difficulty[i];
            jobs[i][1] = profit[i];
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        // Sort workers by ability
        Arrays.sort(worker);
        
        int totalProfit = 0;
        int maxProfit = 0;
        int jobIndex = 0;
        
        // For each worker (in ascending ability order)
        for (int ability : worker) {
            // Add all jobs that this worker can do
            while (jobIndex < n && jobs[jobIndex][0] <= ability) {
                maxProfit = Math.max(maxProfit, jobs[jobIndex][1]);
                jobIndex++;
            }
            // Assign the best job this worker can do
            totalProfit += maxProfit;
        }
        
        return totalProfit;
    }
}