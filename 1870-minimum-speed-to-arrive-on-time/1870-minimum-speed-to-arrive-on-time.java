class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        int left = 1, right = 10000000;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canArrive(dist, hour, mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private boolean canArrive(int[] dist, double hour, int speed) {
        double time = 0;
        for (int i = 0; i < dist.length - 1; i++) {
            time += Math.ceil((double) dist[i] / speed);
        }
        time += (double) dist[dist.length - 1] / speed;
        return time <= hour;
    }
}