/**
 * @param {number[]} data
 * @return {boolean}
 */
var validUtf8 = function(data) {
    let i = 0;
    
    while (i < data.length) {
        // Get the first byte to determine the number of bytes in the character
        const firstByte = data[i];
        
        // Determine the number of bytes for this character
        let numBytes = 0;
        
        // Check for 1-byte character (0xxxxxxx)
        if ((firstByte & 0x80) === 0) { // 0x80 = 10000000
            numBytes = 1;
        }
        // Check for 2-byte character (110xxxxx)
        else if ((firstByte & 0xE0) === 0xC0) { // 0xE0 = 11100000, 0xC0 = 11000000
            numBytes = 2;
        }
        // Check for 3-byte character (1110xxxx)
        else if ((firstByte & 0xF0) === 0xE0) { // 0xF0 = 11110000, 0xE0 = 11100000
            numBytes = 3;
        }
        // Check for 4-byte character (11110xxx)
        else if ((firstByte & 0xF8) === 0xF0) { // 0xF8 = 11111000, 0xF0 = 11110000
            numBytes = 4;
        }
        // Invalid first byte
        else {
            return false;
        }
        
        // Check if we have enough bytes remaining
        if (i + numBytes > data.length) {
            return false;
        }
        
        // For multi-byte characters, check that the following bytes start with '10'
        for (let j = i + 1; j < i + numBytes; j++) {
            const byte = data[j];
            // Check if byte starts with '10' (0x80 = 10000000, 0xC0 = 11000000)
            if ((byte & 0xC0) !== 0x80) {
                return false;
            }
        }
        
        // Move to the next character
        i += numBytes;
    }
    
    return true;
};