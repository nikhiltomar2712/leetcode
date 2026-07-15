/**
 * @param {string} s
 * @return {string}
 */
var originalDigits = function(s) {
    // Count frequency of each character
    const count = {};
    for (const ch of s) {
        count[ch] = (count[ch] || 0) + 1;
    }
    
    // Arrays to store the count of each digit
    const digits = new Array(10).fill(0);
    
    // Step 1: Count digits with unique letters first
    // 'z' is unique to zero
    digits[0] = count['z'] || 0;
    // 'w' is unique to two
    digits[2] = count['w'] || 0;
    // 'u' is unique to four
    digits[4] = count['u'] || 0;
    // 'x' is unique to six
    digits[6] = count['x'] || 0;
    // 'g' is unique to eight
    digits[8] = count['g'] || 0;
    
    // Step 2: Count digits with letters that become unique after removing above
    // 'h' appears in three and eight (eight already counted)
    digits[3] = (count['h'] || 0) - digits[8];
    // 'f' appears in four and five (four already counted)
    digits[5] = (count['f'] || 0) - digits[4];
    // 's' appears in six and seven (six already counted)
    digits[7] = (count['s'] || 0) - digits[6];
    
    // Step 3: Count remaining digits
    // 'o' appears in zero, one, two, four (zero, two, four already counted)
    digits[1] = (count['o'] || 0) - digits[0] - digits[2] - digits[4];
    // 'i' appears in five, six, eight, nine (five, six, eight already counted)
    digits[9] = (count['i'] || 0) - digits[5] - digits[6] - digits[8];
    
    // Build result string
    let result = '';
    for (let i = 0; i < 10; i++) {
        result += String(i).repeat(digits[i]);
    }
    
    return result;
};