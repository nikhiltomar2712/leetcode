/**
 * @param {number[][]} points
 * @return {number}
 */
var maxPoints = function(points) {
    const n = points.length;
    if (n <= 2) return n;
    
    let maxPointsOnLine = 1;
    
    for (let i = 0; i < n; i++) {
        const slopes = new Map();
        let samePoint = 0;
        let currentMax = 0;
        
        for (let j = i + 1; j < n; j++) {
            const dx = points[j][0] - points[i][0];
            const dy = points[j][1] - points[i][1];
            
            // Handle same point (shouldn't happen with unique points but just in case)
            if (dx === 0 && dy === 0) {
                samePoint++;
                continue;
            }
            
            // Get simplified slope as a string key
            const slope = getSlopeKey(dx, dy);
            slopes.set(slope, (slopes.get(slope) || 0) + 1);
            currentMax = Math.max(currentMax, slopes.get(slope));
        }
        
        maxPointsOnLine = Math.max(maxPointsOnLine, currentMax + samePoint + 1);
    }
    
    return maxPointsOnLine;
};

function getSlopeKey(dx, dy) {
    // Reduce to simplest form
    const gcdVal = gcd(Math.abs(dx), Math.abs(dy));
    dx = dx / gcdVal;
    dy = dy / gcdVal;
    
    // Handle negative sign consistently
    if (dx < 0 || (dx === 0 && dy < 0)) {
        dx = -dx;
        dy = -dy;
    }
    
    return `${dx},${dy}`;
}

function gcd(a, b) {
    while (b !== 0) {
        const temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}