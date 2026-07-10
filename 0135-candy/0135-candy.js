/**
 * @param {number[]} ratings
 * @return {number}
 */
var candy = function(ratings) {
    const n = ratings.length;
    if (n === 0) return 0;
    if (n === 1) return 1;
    
    const candies = new Array(n).fill(1);
    
    // Left to right
    for (let i = 1; i < n; i++) {
        if (ratings[i] > ratings[i - 1]) {
            candies[i] = candies[i - 1] + 1;
        }
    }
    
    // Right to left
    for (let i = n - 2; i >= 0; i--) {
        if (ratings[i] > ratings[i + 1]) {
            candies[i] = Math.max(candies[i], candies[i + 1] + 1);
        }
    }
    
    // Calculate sum
    let total = 0;
    for (let i = 0; i < n; i++) {
        total += candies[i];
    }
    
    return total;
};

// Test cases
function runTests() {
    console.log("=== Candy Distribution Tests ===\n");
    
    // Test 1
    let ratings1 = [1,0,2];
    console.log(`Test 1: ratings = [${ratings1}]`);
    console.log(`Output: ${candy(ratings1)}`);
    console.log(`Expected: 5\n`);
    
    // Test 2
    let ratings2 = [1,2,2];
    console.log(`Test 2: ratings = [${ratings2}]`);
    console.log(`Output: ${candy(ratings2)}`);
    console.log(`Expected: 4\n`);
    
    // Test 3 - Increasing sequence
    let ratings3 = [1,2,3,4,5];
    console.log(`Test 3: ratings = [${ratings3}]`);
    console.log(`Output: ${candy(ratings3)}`);
    console.log(`Expected: 15 (1+2+3+4+5)\n`);
    
    // Test 4 - Decreasing sequence
    let ratings4 = [5,4,3,2,1];
    console.log(`Test 4: ratings = [${ratings4}]`);
    console.log(`Output: ${candy(ratings4)}`);
    console.log(`Expected: 15 (5+4+3+2+1)\n`);
    
    // Test 5 - All equal
    let ratings5 = [1,1,1,1,1];
    console.log(`Test 5: ratings = [${ratings5}]`);
    console.log(`Output: ${candy(ratings5)}`);
    console.log(`Expected: 5 (1+1+1+1+1)\n`);
    
    // Test 6 - Valley pattern
    let ratings6 = [1,3,2,2,1];
    console.log(`Test 6: ratings = [${ratings6}]`);
    console.log(`Output: ${candy(ratings6)}`);
    console.log(`Expected: 7 (1+2+1+2+1)\n`);
    
    // Test 7 - Peak pattern
    let ratings7 = [1,2,3,2,1];
    console.log(`Test 7: ratings = [${ratings7}]`);
    console.log(`Output: ${candy(ratings7)}`);
    console.log(`Expected: 9 (1+2+3+2+1)\n`);
    
    // Test 8 - Single element
    let ratings8 = [10];
    console.log(`Test 8: ratings = [${ratings8}]`);
    console.log(`Output: ${candy(ratings8)}`);
    console.log(`Expected: 1\n`);
    
    console.log("=== Tests Complete ===");
}

runTests();