/**
 * @param {number[]} gas
 * @param {number[]} cost
 * @return {number}
 */
var canCompleteCircuit = function(gas, cost) {
    const n = gas.length;
    let totalGas = 0;
    let currentGas = 0;
    let startStation = 0;
    
    for (let i = 0; i < n; i++) {
        const remaining = gas[i] - cost[i];
        totalGas += remaining;
        currentGas += remaining;
        
        if (currentGas < 0) {
            startStation = i + 1;
            currentGas = 0;
        }
    }
    
    return totalGas >= 0 ? startStation : -1;
};

// Test cases
function runTests() {
    console.log("=== Gas Station Tests ===\n");
    
    // Test 1
    let gas1 = [1,2,3,4,5];
    let cost1 = [3,4,5,1,2];
    console.log(`Test 1: gas = [${gas1}], cost = [${cost1}]`);
    console.log(`Output: ${canCompleteCircuit(gas1, cost1)}`);
    console.log(`Expected: 3\n`);
    
    // Test 2
    let gas2 = [2,3,4];
    let cost2 = [3,4,3];
    console.log(`Test 2: gas = [${gas2}], cost = [${cost2}]`);
    console.log(`Output: ${canCompleteCircuit(gas2, cost2)}`);
    console.log(`Expected: -1\n`);
    
    // Test 3 - All stations have enough gas
    let gas3 = [5,5,5,5];
    let cost3 = [3,3,3,3];
    console.log(`Test 3: gas = [${gas3}], cost = [${cost3}]`);
    console.log(`Output: ${canCompleteCircuit(gas3, cost3)}`);
    console.log(`Expected: 0\n`);
    
    // Test 4 - Single station
    let gas4 = [10];
    let cost4 = [5];
    console.log(`Test 4: gas = [${gas4}], cost = [${cost4}]`);
    console.log(`Output: ${canCompleteCircuit(gas4, cost4)}`);
    console.log(`Expected: 0\n`);
    
    // Test 5 - Single station with insufficient gas
    let gas5 = [5];
    let cost5 = [10];
    console.log(`Test 5: gas = [${gas5}], cost = [${cost5}]`);
    console.log(`Output: ${canCompleteCircuit(gas5, cost5)}`);
    console.log(`Expected: -1\n`);
    
    // Test 6 - Perfect balance
    let gas6 = [3,3,3];
    let cost6 = [3,3,3];
    console.log(`Test 6: gas = [${gas6}], cost = [${cost6}]`);
    console.log(`Output: ${canCompleteCircuit(gas6, cost6)}`);
    console.log(`Expected: 0\n`);
    
    // Test 7 - Complex case
    let gas7 = [4,5,2,6,3];
    let cost7 = [3,4,5,2,4];
    console.log(`Test 7: gas = [${gas7}], cost = [${cost7}]`);
    console.log(`Output: ${canCompleteCircuit(gas7, cost7)}`);
    console.log(`Expected: 3\n`);
    
    console.log("=== Tests Complete ===");
}

runTests();