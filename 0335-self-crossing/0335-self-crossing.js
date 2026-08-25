/**
 * @param {number[]} distance
 * @return {boolean}
 */
var isSelfCrossing = function(distance) {
    const n = distance.length;
    
    // Need at least 4 moves to cross
    if (n < 4) return false;
    
    for (let i = 3; i < n; i++) {
        // Case 1: Fourth line crosses first line
        if (distance[i] >= distance[i - 2] && 
            distance[i - 1] <= distance[i - 3]) {
            return true;
        }
        
        // Case 2: Fifth line meets first line
        if (i >= 4 && 
            distance[i - 1] === distance[i - 3] && 
            distance[i] + distance[i - 4] >= distance[i - 2]) {
            return true;
        }
        
        // Case 3: Sixth line crosses first line
        if (i >= 5 && 
            distance[i - 2] >= distance[i - 4] && 
            distance[i] + distance[i - 4] >= distance[i - 2] && 
            distance[i - 1] <= distance[i - 3] && 
            distance[i - 1] + distance[i - 5] >= distance[i - 3]) {
            return true;
        }
    }
    
    return false;
};