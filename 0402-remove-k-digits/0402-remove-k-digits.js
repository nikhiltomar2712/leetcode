/**
 * @param {string} num
 * @param {number} k
 * @return {string}
 */
var removeKdigits = function(num, k) {
    const stack = [];
    
    // If we need to remove all digits
    if (k === num.length) return "0";
    
    for (let digit of num) {
        // While we can remove digits and the stack top is greater than current digit
        while (k > 0 && stack.length > 0 && stack[stack.length - 1] > digit) {
            stack.pop();
            k--;
        }
        stack.push(digit);
    }
    
    // If k is still > 0, remove from the end
    while (k > 0) {
        stack.pop();
        k--;
    }
    
    // Remove leading zeros
    let result = stack.join('').replace(/^0+/, '');
    
    // If result is empty, return "0"
    return result === '' ? '0' : result;
};