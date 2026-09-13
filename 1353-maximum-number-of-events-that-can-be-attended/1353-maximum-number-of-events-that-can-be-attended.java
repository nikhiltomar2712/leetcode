import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int maxEvents(int[][] events) {
        // Sort events by start day
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // stores end days
        int i = 0;
        int n = events.length;
        int count = 0;
        int day = 0;

        while (i < n || !minHeap.isEmpty()) {
            // If heap is empty, jump to the next event's start day
            if (minHeap.isEmpty()) {
                day = events[i][0];
            }

            // Add all events starting on or before 'day'
            while (i < n && events[i][0] <= day) {
                minHeap.offer(events[i][1]);
                i++;
            }

            // Remove expired events (end day < current day)
            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }

            // Attend the event that ends earliest
            if (!minHeap.isEmpty()) {
                minHeap.poll();
                count++;
            }

            day++;
        }

        return count;
    }
}