/**
 * @param {number[]} nums
 * @return {number}
 */
var longestConsecutive = function(nums) {
    if (nums.length === 0) return 0;
    
    // Create a set for O(1) lookups
    const numSet = new Set(nums);
    let longestStreak = 0;
    
    for (const num of numSet) {
        // Only start counting if it's the beginning of a sequence
        // (i.e., num-1 doesn't exist in the set)
        if (!numSet.has(num - 1)) {
            let currentNum = num;
            let currentStreak = 1;
            
            // Count consecutive numbers
            while (numSet.has(currentNum + 1)) {
                currentNum++;
                currentStreak++;
            }
            
            // Update longest streak
            longestStreak = Math.max(longestStreak, currentStreak);
        }
    }
    
    return longestStreak;
};

// Test the function
function runTests() {
    console.log("=== Longest Consecutive Sequence Tests ===");
    
    // Test 1
    let nums = [100,4,200,1,3,2];
    console.log(`Test 1: ${longestConsecutive(nums)}`); // Expected: 4
    
    // Test 2
    nums = [0,3,7,2,5,8,4,6,0,1];
    console.log(`Test 2: ${longestConsecutive(nums)}`); // Expected: 9
    
    // Test 3
    nums = [1,0,1,2];
    console.log(`Test 3: ${longestConsecutive(nums)}`); // Expected: 3
    
    // Test 4 - Empty array
    nums = [];
    console.log(`Test 4: ${longestConsecutive(nums)}`); // Expected: 0
    
    // Test 5 - Single element
    nums = [5];
    console.log(`Test 5: ${longestConsecutive(nums)}`); // Expected: 1
    
    // Test 6 - All duplicates
    nums = [1,1,1,1,1];
    console.log(`Test 6: ${longestConsecutive(nums)}`); // Expected: 1
    
    // Test 7 - Negative numbers
    nums = [-5,-4,-3,0,1,2];
    console.log(`Test 7: ${longestConsecutive(nums)}`); // Expected: 3
    
    console.log("=== Tests Complete ===");
}

// Run the tests
runTests();