/**
 * @param {number[][]} rectangles
 * @return {boolean}
 */
var isRectangleCover = function(rectangles) {
    // Use a Set to track corners
    const cornerSet = new Set();
    let totalArea = 0;
    
    // Initialize boundaries for the overall rectangle
    let minX = Infinity, minY = Infinity;
    let maxX = -Infinity, maxY = -Infinity;
    
    for (const rect of rectangles) {
        const [x1, y1, x2, y2] = rect;
        
        // Update overall boundaries
        minX = Math.min(minX, x1);
        minY = Math.min(minY, y1);
        maxX = Math.max(maxX, x2);
        maxY = Math.max(maxY, y2);
        
        // Calculate area of this rectangle
        totalArea += (x2 - x1) * (y2 - y1);
        
        // Process the four corners
        const corners = [
            `${x1},${y1}`,
            `${x1},${y2}`,
            `${x2},${y1}`,
            `${x2},${y2}`
        ];
        
        for (const corner of corners) {
            if (cornerSet.has(corner)) {
                cornerSet.delete(corner);
            } else {
                cornerSet.add(corner);
            }
        }
    }
    
    // The overall rectangle area
    const overallArea = (maxX - minX) * (maxY - minY);
    if (totalArea !== overallArea) {
        return false;
    }
    
    // The remaining corners should be exactly the four corners of the overall rectangle
    const expectedCorners = new Set([
        `${minX},${minY}`,
        `${minX},${maxY}`,
        `${maxX},${minY}`,
        `${maxX},${maxY}`
    ]);
    
    if (cornerSet.size !== 4) {
        return false;
    }
    
    for (const corner of cornerSet) {
        if (!expectedCorners.has(corner)) {
            return false;
        }
    }
    
    return true;
};