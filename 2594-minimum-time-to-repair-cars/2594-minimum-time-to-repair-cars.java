class Solution {
    public long repairCars(int[] ranks, int cars) {
        long left = 1, right = (long) 1e14;
        while (left < right) {
            long mid = left + (right - left) / 2;
            long total = 0;
            for (int rank : ranks) {
                total += (long) Math.sqrt((double) mid / rank);
            }
            if (total >= cars) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}