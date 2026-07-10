/**
 * @param {string} s
 * @param {string[]} wordDict
 * @return {string[]}
 */
var wordBreak = function(s, wordDict) {
    const wordSet = new Set(wordDict);
    const memo = new Map();
    
    function dfs(start) {
        // If we've already computed results for this starting index
        if (memo.has(start)) {
            return memo.get(start);
        }
        
        const sentences = [];
        
        // If we've reached the end of the string
        if (start === s.length) {
            sentences.push("");
            return sentences;
        }
        
        // Try all possible ending positions
        for (let end = start + 1; end <= s.length; end++) {
            const word = s.substring(start, end);
            
            if (wordSet.has(word)) {
                const subSentences = dfs(end);
                
                for (const subSentence of subSentences) {
                    if (subSentence === "") {
                        sentences.push(word);
                    } else {
                        sentences.push(word + " " + subSentence);
                    }
                }
            }
        }
        
        memo.set(start, sentences);
        return sentences;
    }
    
    return dfs(0);
};