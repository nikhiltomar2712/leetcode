class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        for (int w : weights) {
            left = Math.max(left, w);   // minimum capacity
            right += w;                 // maximum capacity
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canShip(weights, mid, days)) {
                right = mid;            // try smaller capacity
            } else {
                left = mid + 1;         // need larger capacity
            }
        }
        return left;
    }
    
    private boolean canShip(int[] weights, int capacity, int days) {
        int currentLoad = 0;
        int daysNeeded = 1;
        
        for (int w : weights) {
            if (currentLoad + w > capacity) {
                daysNeeded++;
                currentLoad = 0;
            }
            currentLoad += w;
        }
        return daysNeeded <= days;
    }
}