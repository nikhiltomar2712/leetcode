class Solution {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        
        // Pair (nums2[i], nums1[i])
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums2[i];
            pairs[i][1] = nums1[i];
        }
        
        // Sort by nums2 descending
        Arrays.sort(pairs, (a, b) -> Integer.compare(b[0], a[0]));
        
        // Min-heap to keep the k largest nums1 values
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long sum = 0;
        long maxScore = 0;
        
        for (int[] pair : pairs) {
            int num2 = pair[0];
            int num1 = pair[1];
            
            minHeap.offer(num1);
            sum += num1;
            
            // Keep only the k largest nums1 values
            if (minHeap.size() > k) {
                sum -= minHeap.poll();
            }
            
            // When we have exactly k elements, current num2 is a valid minimum
            if (minHeap.size() == k) {
                maxScore = Math.max(maxScore, sum * num2);
            }
        }
        
        return maxScore;
    }
}