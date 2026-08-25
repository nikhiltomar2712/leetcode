/**
 * @param {number[]} nums
 */
var NumArray = function(nums) {
    this.nums = nums; // Keep a copy for the update method
    this.n = nums.length;
    this.bit = new Array(this.n + 1).fill(0); // 1-indexed Fenwick Tree
    
    // Build the Fenwick Tree in O(n log n) - could be optimized to O(n)
    for (let i = 0; i < this.n; i++) {
        this._add(i + 1, nums[i]); // Convert to 1-indexed
    }
};

/**
 * @param {number} index
 * @param {number} val
 * @return {void}
 */
NumArray.prototype.update = function(index, val) {
    const diff = val - this.nums[index];
    this.nums[index] = val;
    this._add(index + 1, diff); // Update the Fenwick Tree
};

/**
 * @param {number} left
 * @param {number} right
 * @return {number}
 */
NumArray.prototype.sumRange = function(left, right) {
    return this._prefixSum(right + 1) - this._prefixSum(left);
};

/**
 * Add a value to the Fenwick Tree at a specific index (1-indexed)
 * @param {number} i
 * @param {number} delta
 * @private
 */
NumArray.prototype._add = function(i, delta) {
    while (i <= this.n) {
        this.bit[i] += delta;
        i += i & -i; // Move to the next index that covers this one
    }
};

/**
 * Get the prefix sum from index 1 to i (1-indexed)
 * @param {number} i
 * @return {number}
 * @private
 */
NumArray.prototype._prefixSum = function(i) {
    let sum = 0;
    while (i > 0) {
        sum += this.bit[i];
        i -= i & -i; // Move to the parent index
    }
    return sum;
};

/**
 * Your NumArray object will be instantiated and called as such:
 * var obj = new NumArray(nums)
 * obj.update(index,val)
 * var param_2 = obj.sumRange(left,right)
 */