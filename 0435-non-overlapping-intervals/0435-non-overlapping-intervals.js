/**
 * @param {number[][]} intervals
 * @return {number}
 */
var eraseOverlapIntervals = function(intervals) {
    if (intervals.length === 0) return 0;
    
    // Sort intervals by end time (greedy choice)
    intervals.sort((a, b) => a[1] - b[1]);
    
    let count = 0; // Number of intervals to remove
    let prevEnd = intervals[0][1]; // End of the first interval
    
    for (let i = 1; i < intervals.length; i++) {
        const [start, end] = intervals[i];
        
        // If current interval overlaps with previous
        if (start < prevEnd) {
            // Need to remove one interval
            count++;
            // Keep the interval that ends earlier (greedy)
            // prevEnd already holds the earlier end, so we just skip current
        } else {
            // No overlap, update prevEnd to current interval's end
            prevEnd = end;
        }
    }
    
    return count;
};