/**
 * @param {string} s
 * @return {number}
 */
var firstUniqChar = function(s) {
    // Array to store frequency of each character (26 lowercase letters)
    const charCount = new Array(26).fill(0);
    
    // First pass: count occurrences of each character
    for (const char of s) {
        const index = char.charCodeAt(0) - 'a'.charCodeAt(0);
        charCount[index]++;
    }
    
    // Second pass: find the first character with count 1
    for (let i = 0; i < s.length; i++) {
        const index = s[i].charCodeAt(0) - 'a'.charCodeAt(0);
        if (charCount[index] === 1) {
            return i;
        }
    }
    
    return -1;
};