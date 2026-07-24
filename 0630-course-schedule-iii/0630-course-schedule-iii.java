class Solution {
    public int scheduleCourse(int[][] courses) {
        // Sort courses by deadline (ascending)
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        
        // Max-heap (priority queue) to store course durations (negative for max-heap)
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        
        int totalTime = 0;
        int count = 0;
        
        for (int[] course : courses) {
            int duration = course[0];
            int deadline = course[1];
            
            // If we can take this course without exceeding deadline
            if (totalTime + duration <= deadline) {
                pq.offer(duration);
                totalTime += duration;
                count++;
            } 
            // If we can't, but replacing the longest course in schedule helps
            else if (!pq.isEmpty() && pq.peek() > duration) {
                totalTime -= pq.poll();
                pq.offer(duration);
                totalTime += duration;
            }
        }
        
        return count;
    }
}