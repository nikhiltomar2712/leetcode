class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        int maxH = 1, curH = 1;
        for (int i = 1; i < hBars.length; i++) {
            if (hBars[i] == hBars[i - 1] + 1) {
                curH++;
            } else {
                curH = 1;
            }
            maxH = Math.max(maxH, curH);
        }
        int maxV = 1, curV = 1;
        for (int i = 1; i < vBars.length; i++) {
            if (vBars[i] == vBars[i - 1] + 1) {
                curV++;
            } else {
                curV = 1;
            }
            maxV = Math.max(maxV, curV);
        }
        int side = Math.min(maxH, maxV) + 1;
        return side * side;
    }
}