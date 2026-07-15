/**
 * @param {string} s
 * @return {number}
 */
var countSegments = function(s) {
    let count = 0;
    
    for (let i = 0; i < s.length; i++) {
        // A segment starts when current char is not a space
        // and either it's the first character or the previous char is a space
        if (s[i] !== ' ' && (i === 0 || s[i - 1] === ' ')) {
            count++;
        }
    }
    
    return count;
};