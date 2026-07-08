class Solution {
    private prefix: number[];

    constructor(w: number[]) {
        this.prefix = new Array(w.length + 1).fill(0);
        for (let i = 0; i < w.length; i++) {
            this.prefix[i + 1] = this.prefix[i] + w[i];
        }
    }

    pickIndex(): number {
        const total = this.prefix[this.prefix.length - 1];
        const target = Math.floor(Math.random() * total) + 1;
        
        // Binary search to find the smallest i such that prefix[i] >= target
        let left = 1;
        let right = this.prefix.length - 1;
        
        while (left < right) {
            const mid = Math.floor((left + right) / 2);
            if (this.prefix[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left - 1; // 0-based index
    }
}