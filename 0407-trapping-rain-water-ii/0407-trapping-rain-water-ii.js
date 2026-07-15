/**
 * @param {number[][]} heightMap
 * @return {number}
 */
var trapRainWater = function(heightMap) {
    if (!heightMap || heightMap.length === 0 || heightMap[0].length === 0) {
        return 0;
    }
    
    const m = heightMap.length;
    const n = heightMap[0].length;
    let water = 0;
    
    // Min-heap implementation using array
    class MinHeap {
        constructor() {
            this.heap = [];
        }
        
        push(val) {
            this.heap.push(val);
            this._heapifyUp(this.heap.length - 1);
        }
        
        pop() {
            if (this.heap.length === 0) return null;
            const min = this.heap[0];
            const last = this.heap.pop();
            if (this.heap.length > 0) {
                this.heap[0] = last;
                this._heapifyDown(0);
            }
            return min;
        }
        
        _heapifyUp(index) {
            while (index > 0) {
                const parent = Math.floor((index - 1) / 2);
                if (this.heap[parent][0] <= this.heap[index][0]) break;
                [this.heap[parent], this.heap[index]] = [this.heap[index], this.heap[parent]];
                index = parent;
            }
        }
        
        _heapifyDown(index) {
            const size = this.heap.length;
            while (true) {
                let smallest = index;
                const left = 2 * index + 1;
                const right = 2 * index + 2;
                
                if (left < size && this.heap[left][0] < this.heap[smallest][0]) {
                    smallest = left;
                }
                if (right < size && this.heap[right][0] < this.heap[smallest][0]) {
                    smallest = right;
                }
                if (smallest === index) break;
                
                [this.heap[index], this.heap[smallest]] = [this.heap[smallest], this.heap[index]];
                index = smallest;
            }
        }
        
        isEmpty() {
            return this.heap.length === 0;
        }
    }
    
    const visited = Array.from({length: m}, () => Array(n).fill(false));
    const heap = new MinHeap();
    
    // Add all boundary cells to the heap
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (i === 0 || i === m - 1 || j === 0 || j === n - 1) {
                heap.push([heightMap[i][j], i, j]);
                visited[i][j] = true;
            }
        }
    }
    
    const directions = [[0, 1], [0, -1], [1, 0], [-1, 0]];
    let maxHeight = 0;
    
    // Process cells in order of increasing height
    while (!heap.isEmpty()) {
        const [height, row, col] = heap.pop();
        maxHeight = Math.max(maxHeight, height);
        
        for (let [dx, dy] of directions) {
            const newRow = row + dx;
            const newCol = col + dy;
            
            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && !visited[newRow][newCol]) {
                visited[newRow][newCol] = true;
                // If current cell height is lower than max boundary height, water can be trapped
                if (heightMap[newRow][newCol] < maxHeight) {
                    water += maxHeight - heightMap[newRow][newCol];
                }
                heap.push([Math.max(heightMap[newRow][newCol], maxHeight), newRow, newCol]);
            }
        }
    }
    
    return water;
};