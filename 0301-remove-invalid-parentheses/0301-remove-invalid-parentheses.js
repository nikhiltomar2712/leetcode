/**
 * @param {string} s
 * @return {string[]}
 */
var removeInvalidParentheses = function(s) {
    // If the string is empty or null, return an array with an empty string
    if (!s) return [""];

    const result = [];
    const queue = [s];
    const visited = new Set();
    visited.add(s);
    let found = false;

    while (queue.length > 0) {
        const current = queue.shift();

        // Check if the current string is valid
        if (isValid(current)) {
            result.push(current);
            found = true;
        }

        // If we already found valid strings at this level, don't generate more removals
        if (found) continue;

        // Generate all possible strings by removing one parenthesis
        for (let i = 0; i < current.length; i++) {
            const char = current[i];
            // Only remove parentheses, not letters
            if (char !== '(' && char !== ')') continue;

            // Create a new string by removing the character at index i
            const next = current.slice(0, i) + current.slice(i + 1);

            // Add to queue if not visited
            if (!visited.has(next)) {
                visited.add(next);
                queue.push(next);
            }
        }
    }

    // If no valid strings found (e.g., input has no parentheses), return [""]
    return result.length > 0 ? result : [""];
};

/**
 * Helper function to check if a string has valid parentheses
 * @param {string} str
 * @return {boolean}
 */
function isValid(str) {
    let count = 0;
    for (const char of str) {
        if (char === '(') {
            count++;
        } else if (char === ')') {
            if (count === 0) return false;
            count--;
        }
    }
    return count === 0;
}