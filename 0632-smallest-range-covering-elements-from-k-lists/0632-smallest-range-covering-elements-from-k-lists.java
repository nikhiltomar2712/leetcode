class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        // Min-heap: [value, listIndex, elementIndex in that list]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        int curMax = Integer.MIN_VALUE;
        int rangeStart = 0;
        int rangeEnd = Integer.MAX_VALUE;
        
        // Initialize heap with first element from each list
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            minHeap.offer(new int[]{val, i, 0});
            curMax = Math.max(curMax, val);
        }
        
        while (minHeap.size() == nums.size()) {
            int[] curr = minHeap.poll();
            int val = curr[0];
            int listIdx = curr[1];
            int elemIdx = curr[2];
            
            // Update smallest range
            if (curMax - val < rangeEnd - rangeStart) {
                rangeStart = val;
                rangeEnd = curMax;
            }
            
            // If there are more elements in this list, add the next one
            if (elemIdx + 1 < nums.get(listIdx).size()) {
                int nextVal = nums.get(listIdx).get(elemIdx + 1);
                minHeap.offer(new int[]{nextVal, listIdx, elemIdx + 1});
                curMax = Math.max(curMax, nextVal);
            }
        }
        
        return new int[]{rangeStart, rangeEnd};
    }
}