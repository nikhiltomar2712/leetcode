class Solution {
    public int maximum69Number(int num) {
        int place = 1;        // current place value (1, 10, 100, ...)
        int targetPlace = 0;  // place value of the leftmost 6 (0 if none)
        int temp = num;
        
        while (temp > 0) {
            if (temp % 10 == 6) {
                targetPlace = place; // keep updating; last assignment = leftmost 6
            }
            temp /= 10;
            place *= 10;
        }
        
        // Changing 6 -> 9 adds 3 at that place value
        return targetPlace == 0 ? num : num + 3 * targetPlace;
    }
}