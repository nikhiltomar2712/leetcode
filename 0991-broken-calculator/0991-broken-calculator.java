class Solution {
    public int brokenCalc(int startValue, int target) {
        int operations = 0;

        // Work backwards from target to startValue
        while (target > startValue) {
            if (target % 2 == 1) {
                // If odd, the only way to get here was by subtracting 1
                // (reverse of subtracting 1 is adding 1)
                target++;
            } else {
                // If even, the previous operation was doubling
                // (reverse of doubling is dividing by 2)
                target /= 2;
            }
            operations++;
        }

        // Once target <= startValue, we only need to subtract
        return operations + (startValue - target);
    }
}