class Solution {
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int maxDistance = 0;
        int lastPerson = -1;
        
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                if (lastPerson == -1) {
                    // Distance from start to first person
                    maxDistance = Math.max(maxDistance, i);
                } else {
                    // Distance between two people (half the gap)
                    maxDistance = Math.max(maxDistance, (i - lastPerson) / 2);
                }
                lastPerson = i;
            }
        }
        
        // Distance from last person to end
        if (lastPerson != -1) {
            maxDistance = Math.max(maxDistance, n - 1 - lastPerson);
        }
        
        return maxDistance;
    }
}