/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 */
var characterReplacement = function(s, k) {
    // Frequency map for characters in current window
    const freq = {};
    let left = 0;
    let maxFreq = 0; // Maximum frequency of any single character in window
    let maxLength = 0;
    
    for (let right = 0; right < s.length; right++) {
        // Add right character to window
        const rightChar = s[right];
        freq[rightChar] = (freq[rightChar] || 0) + 1;
        
        // Update max frequency in current window
        maxFreq = Math.max(maxFreq, freq[rightChar]);
        
        // Window length = right - left + 1
        // If (window length - maxFreq) > k, we need to shrink
        while ((right - left + 1) - maxFreq > k) {
            const leftChar = s[left];
            freq[leftChar]--;
            left++;
            
            // Recalculate maxFreq (optional optimization: we could keep it as is)
            // Recalculation ensures correctness but adds O(26) factor
            // For simplicity and clarity, we recalculate here
            maxFreq = Math.max(...Object.values(freq));
        }
        
        // Update max length
        maxLength = Math.max(maxLength, right - left + 1);
    }
    
    return maxLength;
};