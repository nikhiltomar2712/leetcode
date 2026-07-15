/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 */
var longestSubstring = function(s, k) {
    // Helper function to process a substring
    function helper(start, end) {
        // Base case: if substring length is less than k, it can't be valid
        if (end - start < k) return 0;
        
        // Count frequencies of characters in the current substring
        const freq = {};
        for (let i = start; i < end; i++) {
            const char = s[i];
            freq[char] = (freq[char] || 0) + 1;
        }
        
        // Find a character that appears less than k times
        let splitIndex = -1;
        for (let i = start; i < end; i++) {
            if (freq[s[i]] < k) {
                splitIndex = i;
                break;
            }
        }
        
        // If all characters appear at least k times, this substring is valid
        if (splitIndex === -1) {
            return end - start;
        }
        
        // Otherwise, split at the invalid character and check both sides
        // Skip consecutive invalid characters to avoid redundant recursive calls
        let left = helper(start, splitIndex);
        // Find the end of the invalid character sequence
        let nextStart = splitIndex + 1;
        while (nextStart < end && freq[s[nextStart]] < k) {
            nextStart++;
        }
        let right = helper(nextStart, end);
        
        return Math.max(left, right);
    }
    
    return helper(0, s.length);
};