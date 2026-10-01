class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        boolean[] covered = new boolean[51];
        for (int[] range : ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                covered[i] = true;
            }
        }
        for (int i = left; i <= right; i++) {
            if (!covered[i]) return false;
        }
        return true;
    }
}