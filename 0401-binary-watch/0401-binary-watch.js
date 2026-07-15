/**
 * @param {number} turnedOn
 * @return {string[]}
 */
var readBinaryWatch = function(turnedOn) {
    const result = [];
    
    // Count bits in a number using Brian Kernighan's algorithm
    function countBits(num) {
        let count = 0;
        while (num > 0) {
            num &= (num - 1);  // Clears the lowest set bit
            count++;
        }
        return count;
    }
    
    // Iterate through all possible hours (0-11) and minutes (0-59)
    for (let h = 0; h < 12; h++) {
        for (let m = 0; m < 60; m++) {
            const hourBits = countBits(h);
            const minuteBits = countBits(m);
            
            if (hourBits + minuteBits === turnedOn) {
                // Format: hour:MM (minute with leading zero if needed)
                result.push(`${h}:${m.toString().padStart(2, '0')}`);
            }
        }
    }
    
    return result;
};