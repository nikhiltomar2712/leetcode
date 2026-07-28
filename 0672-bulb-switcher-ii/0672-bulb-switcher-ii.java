class Solution {
    public int flipLights(int n, int presses) {
        n = Math.min(n, 3);
        if (presses == 0) {
            return 1;
        }
        if (presses == 1) {
            return new int[]{2, 3, 4}[n - 1];
        }
        if (presses == 2) {
            return new int[]{2, 4, 7}[n - 1];
        }
        return (int) Math.pow(2, n);  // 2, 4 or 8
    }
}