/**
 * @param {number[]} nums
 */
var Solution = function(nums) {
    // Map to store value -> array of indices
    this.indexMap = new Map();
    
    // Populate the map
    for (let i = 0; i < nums.length; i++) {
        const value = nums[i];
        if (!this.indexMap.has(value)) {
            this.indexMap.set(value, []);
        }
        this.indexMap.get(value).push(i);
    }
};

/** 
 * @param {number} target
 * @return {number}
 */
Solution.prototype.pick = function(target) {
    const indices = this.indexMap.get(target);
    // Randomly select an index from the array
    const randomIndex = Math.floor(Math.random() * indices.length);
    return indices[randomIndex];
};

/** 
 * Your Solution object will be instantiated and called as such:
 * var obj = new Solution(nums)
 * var param_1 = obj.pick(target)
 */