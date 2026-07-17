/**
 * @param {string} s
 * @return {string}
 */
var frequencySort = function(s) {
    // Count frequencies
    const freqMap = new Map();
    let maxFreq = 0;
    
    for (let char of s) {
        const freq = (freqMap.get(char) || 0) + 1;
        freqMap.set(char, freq);
        maxFreq = Math.max(maxFreq, freq);
    }
    
    // Create buckets where bucket[i] contains characters with frequency i
    const buckets = new Array(maxFreq + 1).fill().map(() => []);
    
    for (let [char, freq] of freqMap) {
        buckets[freq].push(char);
    }
    
    // Build result by going from highest frequency to lowest
    let result = '';
    for (let freq = maxFreq; freq >= 1; freq--) {
        if (buckets[freq].length > 0) {
            // Sort characters with same frequency alphabetically
            buckets[freq].sort();
            for (let char of buckets[freq]) {
                result += char.repeat(freq);
            }
        }
    }
    
    return result;
};