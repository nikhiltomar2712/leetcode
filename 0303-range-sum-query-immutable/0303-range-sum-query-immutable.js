/**
 * @param {number[]} nums
 */
var NumArray = function(nums) {
    // Create a prefix sum array where prefix[i] = sum of nums[0...i-1]
    this.prefix = new Array(nums.length + 1).fill(0);
    for (let i = 0; i < nums.length; i++) {
        this.prefix[i + 1] = this.prefix[i] + nums[i];
    }
};

/**
 * @param {number} left
 * @param {number} right
 * @return {number}
 */
NumArray.prototype.sumRange = function(left, right) {
    // Sum of nums[left...right] = prefix[right+1] - prefix[left]
    return this.prefix[right + 1] - this.prefix[left];
};

/**
 * Your NumArray object will be instantiated and called as such:
 * var obj = new NumArray(nums)
 * var param_1 = obj.sumRange(left,right)
 */