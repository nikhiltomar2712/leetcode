/**
 * @param {string} s
 * @param {string} t
 * @return {character}
 */
var findTheDifference = function(s, t) {
    let sumS = 0;
    let sumT = 0;
    
    // Sum character codes of s
    for (const char of s) {
        sumS += char.charCodeAt(0);
    }
    
    // Sum character codes of t
    for (const char of t) {
        sumT += char.charCodeAt(0);
    }
    
    // The difference is the added character's code
    return String.fromCharCode(sumT - sumS);
};