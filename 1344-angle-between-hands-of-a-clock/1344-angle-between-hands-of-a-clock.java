class Solution {
    public double angleClock(int hour, int minutes) {
        // Minute hand: 6 degrees per minute
        double minuteAngle = 6.0 * minutes;
        
        // Hour hand: 30 degrees per hour + 0.5 degrees per minute
        double hourAngle = 30.0 * hour + 0.5 * minutes;
        
        // Absolute difference
        double diff = Math.abs(hourAngle - minuteAngle);
        
        // Smaller angle between the two possible ones
        return Math.min(diff, 360.0 - diff);
    }
}