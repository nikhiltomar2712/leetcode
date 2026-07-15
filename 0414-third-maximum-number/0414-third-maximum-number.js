var thirdMax = function(nums) {
    // Use null to handle cases with -Infinity
    let first = -Infinity;
    let second = -Infinity;
    let third = -Infinity;
    
    for (let num of nums) {
        // Skip duplicates
        if (num === first || num === second || num === third) {
            continue;
        }
        
        if (num > first) {
            third = second;
            second = first;
            first = num;
        } else if (num > second) {
            third = second;
            second = num;
        } else if (num > third) {
            third = num;
        }
    }
    
    // If third max doesn't exist, return first max
    return third === -Infinity ? first : third;
};