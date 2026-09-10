class Solution {
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (hasEvenDigits(num)) {
                count++;
            }
        }
        return count;
    }
    
    private boolean hasEvenDigits(int num) {
        int digits = 0;
        while (num > 0) {
            digits++;
            num /= 10;
        }
        return digits % 2 == 0;
    }
}