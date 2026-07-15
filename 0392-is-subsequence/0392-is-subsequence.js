/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var isSubsequence = function(s, t) {
    // If s is empty, it's trivially a subsequence
    if (s.length === 0) return true;
    
    let i = 0; // Pointer for s
    let j = 0; // Pointer for t
    
    while (j < t.length && i < s.length) {
        if (s[i] === t[j]) {
            i++; // Move to next character in s
        }
        j++; // Always move to next character in t
    }
    
    // If we've matched all characters in s, return true
    return i === s.length;
};