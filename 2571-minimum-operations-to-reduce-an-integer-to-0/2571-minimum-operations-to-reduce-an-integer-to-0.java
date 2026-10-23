class Solution {
    public int minOperations(int n) {
        int count = 0;
        while (n > 0) {
            if ((n & 1) == 0) {
                n >>= 1;
            } else if ((n & 3) == 1 || n == 1) {
                n--;
                count++;
            } else {
                n++;
                count++;
            }
        }
        return count;
    }
}