/**
 * @param {number[][]} points
 * @return {number}
 */
var findMinArrowShots = function(points) {
    if (points.length === 0) return 0;
    
    // Sort by end coordinate
    points.sort((a, b) => a[1] - b[1]);
    
    let arrows = 1;
    let currentEnd = points[0][1];
    
    for (let i = 1; i < points.length; i++) {
        // If current balloon starts after the last arrow position,
        // we need a new arrow
        if (points[i][0] > currentEnd) {
            arrows++;
            currentEnd = points[i][1];
        }
        // Otherwise, the current arrow can burst this balloon too
    }
    
    return arrows;
};