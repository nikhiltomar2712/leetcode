class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n + 2];   // 1-based indexing, +1 for the end marker
        
        // Apply range updates using difference array
        for (int[] booking : bookings) {
            int first = booking[0];
            int last  = booking[1];
            int seats = booking[2];
            
            diff[first] += seats;
            diff[last + 1] -= seats;
        }
        
        // Convert difference array to prefix sum → final answer
        int[] answer = new int[n];
        int curr = 0;
        for (int i = 1; i <= n; i++) {
            curr += diff[i];
            answer[i - 1] = curr;
        }
        
        return answer;
    }
}