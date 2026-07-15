/**
 * @param {string} input
 * @return {number}
 */
var lengthLongestPath = function(input) {
    // Split the input by newline to get each line (file or directory)
    const lines = input.split('\n');
    // Stack to store the cumulative lengths of paths at each depth
    const stack = [];
    let maxLength = 0;
    
    for (const line of lines) {
        // Count the number of tabs to determine the depth
        let depth = 0;
        // Count leading tabs
        while (line[depth] === '\t') {
            depth++;
        }
        
        // Get the actual name (without the leading tabs)
        const name = line.substring(depth);
        const isFile = name.includes('.');
        
        // Pop from stack until we reach the parent directory of the current depth
        // Stack length is the current depth of the last processed item
        while (stack.length > depth) {
            stack.pop();
        }
        
        // If it's a file, calculate the path length and update max
        if (isFile) {
            // Stack contains lengths of directories at each depth
            // Current path length = sum of directory lengths + name length + (number of slashes)
            // Stack: [dir1_len, dir2_len, ...] where each length includes the name length
            // We need to add slashes between each level
            let currentLength = 0;
            // Add lengths of all parent directories
            for (const len of stack) {
                currentLength += len;
            }
            // Add slashes for each level (stack length)
            currentLength += stack.length;
            // Add the name length
            currentLength += name.length;
            
            maxLength = Math.max(maxLength, currentLength);
        } else {
            // It's a directory, push its length to the stack
            // We store the length of the name for cumulative calculation
            stack.push(name.length);
        }
    }
    
    return maxLength;
};