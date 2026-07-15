/**
 * @param {number} n
 * @return {number}
 */
var lastRemaining = function(n) {
    let left = 1;
    let right = n;
    let step = 1;
    let remaining = n;
    let isLeft = true;
    
    while (remaining > 1) {
        if (isLeft || remaining % 2 === 1) {
            left += step;
        }
        
        if (!isLeft || remaining % 2 === 1) {
            right -= step;
        }
        
        step *= 2;
        remaining = Math.floor(remaining / 2);
        isLeft = !isLeft;
    }
    
    return left;
};