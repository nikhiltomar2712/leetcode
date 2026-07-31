/**
 * @param {string[]} words
 * @return {string}
 */
var longestWord = function(words) {
    // Sort lexicographically first
    words.sort();
    
    const built = new Set(['']);  // Start with empty string
    let result = '';
    
    for (const word of words) {
        // Check if the word without its last character can be built
        const prefix = word.slice(0, -1);
        
        if (built.has(prefix)) {
            built.add(word);
            
            // Since array is sorted, first valid word of a given length 
            // is lexicographically smallest
            if (word.length > result.length) {
                result = word;
            }
        }
    }
    
    return result;
};