var findLadders = function(beginWord, endWord, wordList) {
    const wordSet = new Set(wordList);
    if (!wordSet.has(endWord)) return [];

    const res = [];
    const parents = new Map();
    let level = new Set([beginWord]);
    const L = beginWord.length;
    let found = false;

    while (level.size && !found) {
        const nextLevel = new Set();
        const visitedThisLevel = new Set();

        for (let word of level) {
            wordSet.delete(word);
        }

        for (let word of level) {
            for (let i = 0; i < L; i++) {
                for (let c = 97; c <= 122; c++) {
                    const newWord =
                        word.slice(0, i) +
                        String.fromCharCode(c) +
                        word.slice(i + 1);

                    if (!wordSet.has(newWord)) continue;

                    if (!parents.has(newWord)) {
                        parents.set(newWord, []);
                    }
                    parents.get(newWord).push(word);

                    if (newWord === endWord) {
                        found = true;
                    }

                    if (!visitedThisLevel.has(newWord)) {
                        nextLevel.add(newWord);
                        visitedThisLevel.add(newWord);
                    }
                }
            }
        }

        level = nextLevel;
    }

    if (!found) return [];

    function backtrack(word, path) {
        if (word === beginWord) {
            res.push([beginWord, ...path]);
            return;
        }
        for (let parent of parents.get(word)) {
            backtrack(parent, [word, ...path]);
        }
    }

    backtrack(endWord, []);
    return res;
};   