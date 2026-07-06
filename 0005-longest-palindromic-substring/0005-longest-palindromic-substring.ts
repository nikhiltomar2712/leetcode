function longestPalindrome(s: string): string {
    if (s.length <= 1) return s;
    
    let start = 0;
    let maxLength = 1;
    
    for (let i = 0; i < s.length; i++) {
        // Odd length palindrome (center at i)
        let len1 = expandAroundCenter(s, i, i);
        
        // Even length palindrome (center between i and i+1)
        let len2 = expandAroundCenter(s, i, i + 1);
        
        const len = Math.max(len1, len2);
        
        if (len > maxLength) {
            maxLength = len;
            start = i - Math.floor((len - 1) / 2);
        }
    }
    
    return s.substring(start, start + maxLength);
}

function expandAroundCenter(s: string, left: number, right: number): number {
    while (left >= 0 && right < s.length && s[left] === s[right]) {
        left--;
        right++;
    }
    return right - left - 1; // length of palindrome
}