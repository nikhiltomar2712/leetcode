/**
 * @param {number[][]} people
 * @return {number[][]}
 */
var reconstructQueue = function(people) {
    // Sort by height descending, and by k ascending for same height
    people.sort((a, b) => {
        if (a[0] !== b[0]) return b[0] - a[0]; // Taller first
        return a[1] - b[1]; // Same height, smaller k first
    });
    
    const result = [];
    
    // Insert each person at position k
    for (let person of people) {
        result.splice(person[1], 0, person);
    }
    
    return result;
};