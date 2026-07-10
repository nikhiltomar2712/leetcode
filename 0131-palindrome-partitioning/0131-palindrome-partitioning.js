/**
 * @param {string} s
 * @return {string[][]}
 */
var partition = function(s) {
    const result = [];
    const n = s.length;
    
    // Memoization: cache results for substrings
    const memo = new Map();
    
    function isPalindrome(str) {
        let left = 0;
        let right = str.length - 1;
        while (left < right) {
            if (str[left] !== str[right]) return false;
            left++;
            right--;
        }
        return true;
    }
    
    function dfs(start, path) {
        if (start === n) {
            result.push([...path]);
            return;
        }
        
        for (let i = start; i < n; i++) {
            const substr = s.substring(start, i + 1);
            if (isPalindrome(substr)) {
                path.push(substr);
                dfs(i + 1, path);
                path.pop();
            }
        }
    }
    
    dfs(0, []);
    return result;
};