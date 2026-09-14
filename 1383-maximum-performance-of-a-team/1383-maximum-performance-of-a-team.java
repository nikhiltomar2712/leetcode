class Solution {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int MOD = 1_000_000_007;
        
        // Create array of [efficiency, speed]
        int[][] engineers = new int[n][2];
        for (int i = 0; i < n; i++) {
            engineers[i][0] = efficiency[i];
            engineers[i][1] = speed[i];
        }
        
        // Sort by efficiency descending
        Arrays.sort(engineers, (a, b) -> b[0] - a[0]);
        
        // Min-heap to keep the k largest speeds
        PriorityQueue<Integer> speedHeap = new PriorityQueue<>();
        long speedSum = 0;
        long maxPerf = 0;
        
        for (int[] eng : engineers) {
            int currEff = eng[0];
            int currSpeed = eng[1];
            
            // Add current speed
            speedHeap.offer(currSpeed);
            speedSum += currSpeed;
            
            // If more than k engineers, remove the one with smallest speed
            if (speedHeap.size() > k) {
                speedSum -= speedHeap.poll();
            }
            
            // Current performance = speedSum * min efficiency (currEff)
            maxPerf = Math.max(maxPerf, speedSum * currEff);
        }
        
        return (int) (maxPerf % MOD);
    }
}