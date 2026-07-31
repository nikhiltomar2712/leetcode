class MyCalendarThree {
    // TreeMap to store timeline events: time -> change in active bookings
    private TreeMap<Integer, Integer> timeline;
    
    public MyCalendarThree() {
        timeline = new TreeMap<>();
    }
    
    public int book(int startTime, int endTime) {
        // Add +1 at start, -1 at end (half-open interval)
        timeline.put(startTime, timeline.getOrDefault(startTime, 0) + 1);
        timeline.put(endTime, timeline.getOrDefault(endTime, 0) - 1);
        
        // Sweep through timeline to find maximum concurrent bookings
        int maxBooking = 0;
        int currentBooking = 0;
        
        for (int count : timeline.values()) {
            currentBooking += count;
            maxBooking = Math.max(maxBooking, currentBooking);
        }
        
        return maxBooking;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(startTime,endTime);
 */