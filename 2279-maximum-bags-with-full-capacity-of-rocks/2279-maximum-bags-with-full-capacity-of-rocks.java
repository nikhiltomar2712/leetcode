class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(diff);
        int count = 0;
        for (int d : diff) {
            if (additionalRocks >= d) {
                additionalRocks -= d;
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}