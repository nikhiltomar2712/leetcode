class Solution {
    public boolean isReachable(int targetX, int targetY) {
        int g = gcd(targetX, targetY);
        return (g & (g - 1)) == 0;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}