class Solution {
    public int getLastMoment(int n, int[] left, int[] right) {
        int maxTime = 0;

        // Ants moving left: fall off left end, time = position
        for (int pos : left) {
            maxTime = Math.max(maxTime, pos);
        }

        // Ants moving right: fall off right end, time = n - position
        for (int pos : right) {
            maxTime = Math.max(maxTime, n - pos);
        }

        return maxTime;
    }
}