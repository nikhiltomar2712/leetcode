/**
 * @param {string} s
 * @return {number}
 */
var minCut = function(s) {
    const n = s.length;
    
    // Step 1: Create palindrome lookup table
    const pal = Array(n).fill().map(() => Array(n).fill(false));
    
    // Fill table: all single characters are palindromes
    for (let i = 0; i < n; i++) {
        pal[i][i] = true;
    }
    
    // Check for length 2
    for (let i = 0; i < n - 1; i++) {
        if (s[i] === s[i + 1]) {
            pal[i][i + 1] = true;
        }
    }
    
    // Check for length >= 3
    for (let len = 3; len <= n; len++) {
        for (let i = 0; i <= n - len; i++) {
            const j = i + len - 1;
            if (s[i] === s[j] && pal[i + 1][j - 1]) {
                pal[i][j] = true;
            }
        }
    }
    
    // Step 2: DP for minimum cuts
    // cuts[i] = min cuts for s[0..i]
    const cuts = new Array(n);
    
    for (let i = 0; i < n; i++) {
        // If entire substring s[0..i] is palindrome, no cuts needed
        if (pal[0][i]) {
            cuts[i] = 0;
            continue;
        }
        
        // Start with maximum possible cuts
        cuts[i] = i;
        
        // Try all possible partition points
        for (let j = 0; j < i; j++) {
            // If s[j+1..i] is palindrome
            if (pal[j + 1][i]) {
                cuts[i] = Math.min(cuts[i], cuts[j] + 1);
            }
        }
    }
    
    return cuts[n - 1];
};