/**
 * @param {string} s
 * @param {string[]} wordDict
 * @return {boolean}
 */
var wordBreak = function(s, wordDict) {
    const n = s.length;
    const wordSet = new Set(wordDict);
    
    // dp[i] = true if s[0..i-1] can be segmented
    const dp = new Array(n + 1).fill(false);
    dp[0] = true; // Empty string is always segmentable
    
    for (let i = 1; i <= n; i++) {
        for (let j = 0; j < i; j++) {
            // If s[0..j-1] is segmentable and s[j..i-1] is a word
            if (dp[j] && wordSet.has(s.substring(j, i))) {
                dp[i] = true;
                break; // Found a valid segmentation
            }
        }
    }
    
    return dp[n];
};