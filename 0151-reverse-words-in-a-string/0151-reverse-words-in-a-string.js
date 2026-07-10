/**
 * @param {string} s
 * @return {string}
 */
var reverseWords = function(s) {
    // Extract words manually
    const words = [];
    let word = '';
    
    for (let i = 0; i < s.length; i++) {
        if (s[i] !== ' ') {
            word += s[i];
        } else if (word.length > 0) {
            words.push(word);
            word = '';
        }
    }
    
    // Don't forget the last word
    if (word.length > 0) {
        words.push(word);
    }
    
    // Build result in reverse order
    let result = '';
    for (let i = words.length - 1; i >= 0; i--) {
        result += words[i];
        if (i > 0) {
            result += ' ';
        }
    }
    
    return result;
};