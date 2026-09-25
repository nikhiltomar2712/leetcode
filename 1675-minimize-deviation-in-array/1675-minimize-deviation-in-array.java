import java.util.*;

class Solution {
    public int minimumDeviation(int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int minVal = Integer.MAX_VALUE;
        
        // Step 1: Normalize - multiply all odd numbers by 2
        // This makes all numbers even, so we can only decrease them later
        for (int num : nums) {
            if (num % 2 == 1) {
                num *= 2;
            }
            maxHeap.offer(num);
            minVal = Math.min(minVal, num);
        }
        
        int minDeviation = Integer.MAX_VALUE;
        
        // Step 2: Greedily reduce the maximum while it's even
        while (!maxHeap.isEmpty()) {
            int maxVal = maxHeap.poll();
            
            // Update minimum deviation
            minDeviation = Math.min(minDeviation, maxVal - minVal);
            
            // If max is odd, we can't reduce it further
            if (maxVal % 2 == 1) {
                break;
            }
            
            // Reduce max by 2 and update min if needed
            int reduced = maxVal / 2;
            minVal = Math.min(minVal, reduced);
            maxHeap.offer(reduced);
        }
        
        return minDeviation;
    }
}