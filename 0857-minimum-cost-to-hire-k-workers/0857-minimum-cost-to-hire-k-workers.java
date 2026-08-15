class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        
        // workers[i] = {ratio = wage/quality, quality}
        double[][] workers = new double[n][2];
        for (int i = 0; i < n; i++) {
            workers[i][0] = (double) wage[i] / quality[i];
            workers[i][1] = quality[i];
        }
        
        // Sort by increasing wage/quality ratio
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
        
        // Max-heap to keep the k workers with largest quality
        // (so we can remove the most expensive ones when needed)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        double qualitySum = 0;
        double ans = Double.MAX_VALUE;
        
        for (double[] worker : workers) {
            double ratio = worker[0];
            int q = (int) worker[1];
            
            qualitySum += q;
            maxHeap.offer(q);
            
            // Keep only k workers (remove the one with highest quality)
            if (maxHeap.size() > k) {
                qualitySum -= maxHeap.poll();
            }
            
            // When we have exactly k workers, current ratio is the maximum ratio
            // in the group → total cost = qualitySum * ratio
            if (maxHeap.size() == k) {
                ans = Math.min(ans, qualitySum * ratio);
            }
        }
        
        return ans;
    }
}