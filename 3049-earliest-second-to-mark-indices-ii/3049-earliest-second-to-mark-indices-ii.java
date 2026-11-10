class Solution {
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        long numsSum = 0;
        for (int num : nums) {
            numsSum += num;
        }
        Map<Integer, Integer> secondToIndex = getSecondToIndex(nums, changeIndices);
        int l = 0;
        int r = changeIndices.length + 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (canMark(nums, secondToIndex, m, numsSum)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l <= changeIndices.length ? l : -1;
    }

    // Returns true if all indices of nums can be marked within maxSecond.
    private boolean canMark(int[] nums, Map<Integer, Integer> secondToIndex, int maxSecond, long numsSum) {
        // Min-heap to greedily keep the smallest numbers (least savings from set-to-zero).
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int marks = 0; // free seconds available for marking

        for (int second = maxSecond - 1; second >= 0; --second) {
            if (secondToIndex.containsKey(second)) {
                int index = secondToIndex.get(second);
                minHeap.offer(nums[index]);
                if (marks == 0) {
                    // No free mark slot left → must use this second for a mark,
                    // so discard the cheapest (smallest) candidate.
                    minHeap.poll();
                    ++marks;
                } else {
                    // Use this second to set a number to zero; consume one mark slot.
                    --marks;
                }
            } else {
                // No set opportunity → this second can be used for a mark.
                ++marks;
            }
        }

        int heapSize = minHeap.size();
        long heapSum = 0;
        while (!minHeap.isEmpty()) {
            heapSum += minHeap.poll();
        }
        // Cost of decrementing + marking the indices that were not set to zero
        long decrementAndMarkCost = numsSum - heapSum + (nums.length - heapSize);
        // Cost of setting + marking the indices that were set to zero
        long zeroAndMarkCost = 2L * heapSize;
        return decrementAndMarkCost + zeroAndMarkCost <= maxSecond;
    }

    // Map each earliest usable set-second → the corresponding index.
    private Map<Integer, Integer> getSecondToIndex(int[] nums, int[] changeIndices) {
        Map<Integer, Integer> indexToFirstSecond = new HashMap<>();
        for (int s = 0; s < changeIndices.length; ++s) {
            int idx = changeIndices[s] - 1; // convert to 0-based
            if (nums[idx] > 0) {
                indexToFirstSecond.putIfAbsent(idx, s);
            }
        }
        Map<Integer, Integer> secondToIndex = new HashMap<>();
        for (Map.Entry<Integer, Integer> e : indexToFirstSecond.entrySet()) {
            secondToIndex.put(e.getValue(), e.getKey());
        }
        return secondToIndex;
    }
}