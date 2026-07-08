function findLUSlength(strs: string[]): number {
    // Helper function to check if b is subsequence of a
    function isSubsequence(a: string, b: string): boolean {
        let i = 0;
        for (const char of a) {
            if (i < b.length && char === b[i]) {
                i++;
            }
        }
        return i === b.length;
    }

    // Sort strings by length descending
    strs.sort((a, b) => b.length - a.length);

    for (let i = 0; i < strs.length; i++) {
        let isUnique = true;
        for (let j = 0; j < strs.length; j++) {
            if (i !== j && isSubsequence(strs[j], strs[i])) {
                isUnique = false;
                break;
            }
        }
        if (isUnique) {
            return strs[i].length;
        }
    }

    return -1;
}