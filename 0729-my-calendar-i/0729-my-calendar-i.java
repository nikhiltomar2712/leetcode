class MyCalendar {
    // List to store booked intervals as [start, end]
    private List<int[]> bookings;
    
    public MyCalendar() {
        bookings = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        // Check for overlap with existing bookings
        for (int[] interval : bookings) {
            int existingStart = interval[0];
            int existingEnd = interval[1];
            
            // Check if [start, end) overlaps with [existingStart, existingEnd)
            // Overlap condition: start < existingEnd && end > existingStart
            if (start < existingEnd && end > existingStart) {
                return false; // Double booking detected
            }
        }
        
        // No overlap, add the booking
        bookings.add(new int[]{start, end});
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */