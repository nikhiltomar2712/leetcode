/**
 * @param {number[]} target
 * @return {boolean}
 */
var isPossible = function(target) {
    // Handle edge cases
    if (target.length === 0) return false;
    if (target.length === 1) return target[0] === 1;
    
    // Use array as max heap with helper functions
    const heap = [];
    let sum = 0;
    
    // Helper functions for heap operations
    function insertHeap(val) {
        heap.push(val);
        let index = heap.length - 1;
        while (index > 0) {
            const parentIndex = Math.floor((index - 1) / 2);
            if (heap[parentIndex] >= heap[index]) break;
            [heap[parentIndex], heap[index]] = [heap[index], heap[parentIndex]];
            index = parentIndex;
        }
    }
    
    function extractMax() {
        const max = heap[0];
        const last = heap.pop();
        if (heap.length > 0) {
            heap[0] = last;
            let index = 0;
            const length = heap.length;
            while (true) {
                let maxIndex = index;
                const leftChild = 2 * index + 1;
                const rightChild = 2 * index + 2;
                if (leftChild < length && heap[leftChild] > heap[maxIndex]) {
                    maxIndex = leftChild;
                }
                if (rightChild < length && heap[rightChild] > heap[maxIndex]) {
                    maxIndex = rightChild;
                }
                if (maxIndex === index) break;
                [heap[index], heap[maxIndex]] = [heap[maxIndex], heap[index]];
                index = maxIndex;
            }
        }
        return max;
    }
    
    // Add all elements to the heap and calculate initial sum
    for (const num of target) {
        insertHeap(num);
        sum += num;
    }
    
    while (true) {
        const maxVal = extractMax();
        
        // If max is 1, all elements are 1, we succeeded
        if (maxVal === 1) return true;
        
        // Calculate sum of all other elements
        const othersSum = sum - maxVal;
        
        // If othersSum is 0 or negative, impossible
        if (othersSum <= 0) return false;
        
        // If max is less than or equal to others sum, we can't reduce it further
        if (maxVal <= othersSum) return false;
        
        // Calculate what the previous value was using modulo optimization
        let newVal = maxVal % othersSum;
        
        // Handle the case where modulo gives 0
        if (newVal === 0) {
            newVal = othersSum;
        }
        
        // Update sum: remove old max, add new value
        sum = othersSum + newVal;
        
        // Add back the reduced value
        insertHeap(newVal);
    }
};

// Test cases
console.log(isPossible([9, 3, 5])); // true
console.log(isPossible([1, 1, 1, 2])); // false
console.log(isPossible([8, 5])); // true