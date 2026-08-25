/**
 * @param {string} s
 * @return {string}
 */
var reverseVowels = function(s) {
    const vowels = new Set(['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U']);
    const chars = s.split('');
    let left = 0;
    let right = chars.length - 1;
    
    while (left < right) {
        // Find next vowel from left
        while (left < right && !vowels.has(chars[left])) {
            left++;
        }
        
        // Find next vowel from right
        while (left < right && !vowels.has(chars[right])) {
            right--;
        }
        
        // Swap vowels
        if (left < right) {
            [chars[left], chars[right]] = [chars[right], chars[left]];
            left++;
            right--;
        }
    }
    
    return chars.join('');
};