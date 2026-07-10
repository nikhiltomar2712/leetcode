/**
 * @param {number} columnNumber
 * @return {string}
 */
var convertToTitle = function(columnNumber) {
    let result = "";
    
    while (columnNumber > 0) {
        // Convert to 0-indexed (A=0, B=1, ..., Z=25)
        columnNumber--;
        
        // Get the current digit (0-25)
        const remainder = columnNumber % 26;
        
        // Convert to character: 65 is ASCII for 'A'
        result = String.fromCharCode(65 + remainder) + result;
        
        // Move to next digit
        columnNumber = Math.floor(columnNumber / 26);
    }
    
    return result;
};