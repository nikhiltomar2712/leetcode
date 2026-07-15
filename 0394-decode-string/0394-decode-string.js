/**
 * @param {string} s
 * @return {string}
 */
var decodeString = function(s) {
    const countStack = [];
    const stringStack = [];
    let currentString = '';
    let currentCount = 0;
    
    for (const char of s) {
        if (char >= '0' && char <= '9') {
            // Build the number (could be multi-digit)
            currentCount = currentCount * 10 + parseInt(char);
        } else if (char === '[') {
            // Push the current count and string to their stacks
            countStack.push(currentCount);
            stringStack.push(currentString);
            // Reset for the new nested string
            currentCount = 0;
            currentString = '';
        } else if (char === ']') {
            // Pop the previous count and string
            const repeatCount = countStack.pop();
            const previousString = stringStack.pop();
            // Repeat currentString and append to previousString
            currentString = previousString + currentString.repeat(repeatCount);
        } else {
            // Regular character: append to currentString
            currentString += char;
        }
    }
    
    return currentString;
};