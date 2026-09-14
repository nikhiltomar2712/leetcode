class Solution {
    public int[] closestDivisors(int num) {
        int[] pair1 = findClosestPair(num + 1);
        int[] pair2 = findClosestPair(num + 2);
        
        // Return the pair with smaller absolute difference
        if (Math.abs(pair1[0] - pair1[1]) <= Math.abs(pair2[0] - pair2[1])) {
            return pair1;
        }
        return pair2;
    }
    
    private int[] findClosestPair(int x) {
        // Start from sqrt(x) and go downwards
        for (int i = (int) Math.sqrt(x); i >= 1; i--) {
            if (x % i == 0) {
                return new int[]{i, x / i};
            }
        }
        // This line is never reached for x >= 1
        return new int[]{1, x};
    }
}