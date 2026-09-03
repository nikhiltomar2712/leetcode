class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        // Locations are in range [0, 1000]
        int[] diff = new int[1001];

        for (int[] trip : trips) {
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            diff[from] += passengers;   // pick up
            diff[to] -= passengers;     // drop off
        }

        int currentPassengers = 0;
        for (int i = 0; i <= 1000; i++) {
            currentPassengers += diff[i];
            if (currentPassengers > capacity) {
                return false;
            }
        }

        return true;
    }
}