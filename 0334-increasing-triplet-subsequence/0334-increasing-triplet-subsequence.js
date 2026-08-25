/**
 * @param {number[]} nums
 * @return {boolean}
 */
var increasingTriplet = function(nums) {
    let first = Infinity;
    let second = Infinity;
    
    for (const num of nums) {
        if (num <= first) {
            first = num;
        } else if (num <= second) {
            second = num;
        } else {
            // Found a number greater than both first and second
            return true;
        }
    }
    
    return false;
};