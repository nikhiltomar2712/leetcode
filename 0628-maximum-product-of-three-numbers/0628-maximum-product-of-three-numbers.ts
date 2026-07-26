function maximumProduct(nums: number[]): number {
    let min1 = Infinity, min2 = Infinity;
    let max1 = -Infinity, max2 = -Infinity, max3 = -Infinity;

    for (const x of nums) {
        // Track two smallest
        if (x < min1) {
            min2 = min1;
            min1 = x;
        } else if (x < min2) {
            min2 = x;
        }

        // Track three largest
        if (x > max1) {
            max3 = max2;
            max2 = max1;
            max1 = x;
        } else if (x > max2) {
            max3 = max2;
            max2 = x;
        } else if (x > max3) {
            max3 = x;
        }
    }

    return Math.max(min1 * min2 * max1, max1 * max2 * max3);
}