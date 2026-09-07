class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int total = 0;
        for (int d : distance) {
            total += d;
        }

        int clockwise = 0;
        int n = distance.length;
        int curr = start;

        while (curr != destination) {
            clockwise += distance[curr];
            curr = (curr + 1) % n;
        }

        return Math.min(clockwise, total - clockwise);
    }
}