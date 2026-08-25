/**
 * @param {string} secret
 * @param {string} guess
 * @return {string}
 */
var getHint = function(secret, guess) {
    let bulls = 0;
    let cows = 0;
    const secretCount = new Array(10).fill(0);
    const guessCount = new Array(10).fill(0);

    // First pass: count bulls and record non-bull digits
    for (let i = 0; i < secret.length; i++) {
        const s = parseInt(secret[i]);
        const g = parseInt(guess[i]);

        if (s === g) {
            bulls++;
        } else {
            // For non-bull digits, count their occurrences
            secretCount[s]++;
            guessCount[g]++;
        }
    }

    // Second pass: count cows by taking the minimum of matching digits
    for (let i = 0; i < 10; i++) {
        cows += Math.min(secretCount[i], guessCount[i]);
    }

    return `${bulls}A${cows}B`;
};