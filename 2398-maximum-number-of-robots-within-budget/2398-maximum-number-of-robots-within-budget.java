class Solution {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length;
        Deque<Integer> deque = new ArrayDeque<>();
        long sum = 0;
        int left = 0, res = 0;
        for (int right = 0; right < n; right++) {
            while (!deque.isEmpty() && chargeTimes[deque.peekLast()] <= chargeTimes[right]) {
                deque.pollLast();
            }
            deque.offerLast(right);
            sum += runningCosts[right];
            while (!deque.isEmpty() && chargeTimes[deque.peekFirst()] + (long) (right - left + 1) * sum > budget) {
                if (deque.peekFirst() == left) deque.pollFirst();
                sum -= runningCosts[left];
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}