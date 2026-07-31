class MyCalendarTwo {
    // List to store all single booked intervals
    private List<int[]> singleBookings;
    // List to store all double booked intervals
    private List<int[]> doubleBookings;
    
    public MyCalendarTwo() {
        singleBookings = new ArrayList<>();
        doubleBookings = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        // Step 1: Check if this booking would cause a triple booking
        for (int[] interval : doubleBookings) {
            int existingStart = interval[0];
            int existingEnd = interval[1];
            
            // Check for overlap with double booked intervals
            if (start < existingEnd && end > existingStart) {
                return false; // Would create a triple booking
            }
        }
        
        // Step 2: Check for overlaps with single bookings
        // Any overlap with single booking becomes a double booking
        for (int[] interval : singleBookings) {
            int existingStart = interval[0];
            int existingEnd = interval[1];
            
            // Check for overlap
            if (start < existingEnd && end > existingStart) {
                // Add the overlapping part to double bookings
                int overlapStart = Math.max(start, existingStart);
                int overlapEnd = Math.min(end, existingEnd);
                doubleBookings.add(new int[]{overlapStart, overlapEnd});
            }
        }
        
        // Step 3: Add the new booking to single bookings
        singleBookings.add(new int[]{start, end});
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */