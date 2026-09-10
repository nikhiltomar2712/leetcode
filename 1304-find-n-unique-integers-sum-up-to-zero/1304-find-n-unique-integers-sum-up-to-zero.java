class Solution {
    public int[] sumZero(int n) {
        int[] result = new int[n];
        int index = 0;
        
        // Fill with symmetric pairs: 1, -1, 2, -2, 3, -3, ...
        for (int i = 1; i <= n / 2; i++) {
            result[index++] = i;
            result[index++] = -i;
        }
        
        // If n is odd, the last element is 0
        if (n % 2 == 1) {
            result[index] = 0;
        }
        
        return result;
    }
}