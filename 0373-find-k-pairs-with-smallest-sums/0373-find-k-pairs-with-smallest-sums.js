/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @param {number} k
 * @return {number[][]}
 */
var kSmallestPairs = function(nums1, nums2, k) {
    // If either array is empty or k is 0, return empty array
    if (!nums1.length || !nums2.length || k === 0) return [];
    
    const result = [];
    const minHeap = [];
    
    // Initialize the heap with pairs (nums1[0], nums2[i]) for i up to min(k, nums2.length)
    // This covers the smallest element from nums1 paired with first k elements from nums2
    for (let i = 0; i < Math.min(k, nums2.length); i++) {
        minHeap.push([nums1[0] + nums2[i], 0, i]); // [sum, index1, index2]
    }
    
    // Build min heap
    heapify(minHeap);
    
    // Extract k pairs
    while (k > 0 && minHeap.length > 0) {
        // Pop the smallest sum pair
        const [sum, i, j] = popHeap(minHeap);
        result.push([nums1[i], nums2[j]]);
        k--;
        
        // If there's a next element in nums1, push (nums1[i+1], nums2[j]) to heap
        if (i + 1 < nums1.length) {
            pushHeap(minHeap, [nums1[i + 1] + nums2[j], i + 1, j]);
        }
    }
    
    return result;
};

// Helper: Build min heap from array
function heapify(heap) {
    for (let i = Math.floor(heap.length / 2) - 1; i >= 0; i--) {
        sinkDown(heap, i);
    }
}

// Helper: Push element to heap
function pushHeap(heap, element) {
    heap.push(element);
    bubbleUp(heap, heap.length - 1);
}

// Helper: Pop minimum element from heap
function popHeap(heap) {
    if (heap.length === 1) return heap.pop();
    
    const min = heap[0];
    heap[0] = heap.pop();
    sinkDown(heap, 0);
    return min;
}

// Helper: Bubble up element at index
function bubbleUp(heap, index) {
    while (index > 0) {
        const parentIndex = Math.floor((index - 1) / 2);
        if (heap[index][0] >= heap[parentIndex][0]) break;
        [heap[index], heap[parentIndex]] = [heap[parentIndex], heap[index]];
        index = parentIndex;
    }
}

// Helper: Sink down element at index
function sinkDown(heap, index) {
    const length = heap.length;
    while (true) {
        let smallest = index;
        const leftChild = 2 * index + 1;
        const rightChild = 2 * index + 2;
        
        if (leftChild < length && heap[leftChild][0] < heap[smallest][0]) {
            smallest = leftChild;
        }
        if (rightChild < length && heap[rightChild][0] < heap[smallest][0]) {
            smallest = rightChild;
        }
        
        if (smallest === index) break;
        
        [heap[index], heap[smallest]] = [heap[smallest], heap[index]];
        index = smallest;
    }
}