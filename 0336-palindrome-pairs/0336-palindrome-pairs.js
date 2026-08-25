/**
 * @param {string[]} words
 * @return {number[][]}
 */
var palindromePairs = function(words) {
    const map = new Map();
    const result = [];
    
    // Store all words with their indices
    for (let i = 0; i < words.length; i++) {
        map.set(words[i], i);
    }
    
    for (let i = 0; i < words.length; i++) {
        const word = words[i];
        const len = word.length;
        
        // Try all possible split points
        for (let j = 0; j <= len; j++) {
            const prefix = word.substring(0, j);
            const suffix = word.substring(j);
            
            // Case 1: If prefix is palindrome, check if reverse of suffix exists
            if (isPalindrome(prefix)) {
                const reversedSuffix = suffix.split('').reverse().join('');
                if (map.has(reversedSuffix) && map.get(reversedSuffix) !== i) {
                    result.push([map.get(reversedSuffix), i]);
                }
            }
            
            // Case 2: If suffix is palindrome, check if reverse of prefix exists
            if (j !== len && isPalindrome(suffix)) {
                const reversedPrefix = prefix.split('').reverse().join('');
                if (map.has(reversedPrefix) && map.get(reversedPrefix) !== i) {
                    result.push([i, map.get(reversedPrefix)]);
                }
            }
        }
    }
    
    return result;
};

function isPalindrome(str) {
    let left = 0;
    let right = str.length - 1;
    
    while (left < right) {
        if (str[left] !== str[right]) {
            return false;
        }
        left++;
        right--;
    }
    
    return true;
}