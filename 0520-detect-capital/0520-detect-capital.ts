function detectCapitalUse(word: string): boolean {
    if (word.length === 0) return true;
    
    // All uppercase
    if (word === word.toUpperCase()) return true;
    
    // All lowercase
    if (word === word.toLowerCase()) return true;
    
    // First capital, rest lowercase
    return word[0] === word[0].toUpperCase() && 
           word.slice(1) === word.slice(1).toLowerCase();
}