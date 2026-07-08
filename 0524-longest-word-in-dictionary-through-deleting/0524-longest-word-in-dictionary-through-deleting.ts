function findLongestWord(s: string, dictionary: string[]): string {
    let longest = "";
    
    for (const word of dictionary) {
        if (word.length < longest.length) continue;
        if (word.length === longest.length && word > longest) continue;
        
        let i = 0; // pointer for word
        for (const char of s) {
            if (i < word.length && char === word[i]) {
                i++;
            }
        }
        
        if (i === word.length) {
            if (word.length > longest.length || 
                (word.length === longest.length && word < longest)) {
                longest = word;
            }
        }
    }
    
    return longest;
}