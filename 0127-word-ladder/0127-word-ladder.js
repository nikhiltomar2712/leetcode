/**
 * @param {string} beginWord
 * @param {string} endWord
 * @param {string[]} wordList
 * @return {number}
 */
var ladderLength = function(beginWord, endWord, wordList) {
    const wordSet = new Set(wordList);
    if (!wordSet.has(endWord)) return 0;
    
    let beginSet = new Set([beginWord]);
    let endSet = new Set([endWord]);
    const visited = new Set([beginWord, endWord]);
    let distance = 2;
    
    while (beginSet.size > 0 && endSet.size > 0) {
        // Always expand the smaller set for optimization
        if (beginSet.size > endSet.size) {
            [beginSet, endSet] = [endSet, beginSet];
        }
        
        const nextSet = new Set();
        
        for (const word of beginSet) {
            const wordArr = word.split('');
            
            for (let i = 0; i < wordArr.length; i++) {
                const originalChar = wordArr[i];
                
                for (let c = 97; c <= 122; c++) {
                    const newChar = String.fromCharCode(c);
                    if (newChar === originalChar) continue;
                    
                    wordArr[i] = newChar;
                    const nextWord = wordArr.join('');
                    
                    if (endSet.has(nextWord)) {
                        return distance;
                    }
                    
                    if (wordSet.has(nextWord) && !visited.has(nextWord)) {
                        visited.add(nextWord);
                        nextSet.add(nextWord);
                    }
                }
                
                wordArr[i] = originalChar;
            }
        }
        
        beginSet = nextSet;
        distance++;
    }
    
    return 0;
};

// Test
console.log(ladderLength("hit", "cog", ["hot","dot","dog","lot","log","cog"])); // 5
console.log(ladderLength("hit", "cog", ["hot","dot","dog","lot","log"])); // 0