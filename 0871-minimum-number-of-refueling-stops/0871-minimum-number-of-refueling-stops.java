class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        // Max-heap of fuel amounts from stations we have passed
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        
        int n = stations.length;
        int ans = 0;
        int prevPos = 0;
        int fuel = startFuel;
        
        // Process all stations + the target as a final "station"
        for (int i = 0; i <= n; i++) {
            int currPos = (i < n) ? stations[i][0] : target;
            int dist = currPos - prevPos;
            
            fuel -= dist;   // consume fuel to reach current position
            
            // If not enough fuel, refuel greedily from the best stations we've passed
            while (fuel < 0 && !maxHeap.isEmpty()) {
                fuel += maxHeap.poll();
                ans++;
            }
            
            // Still not enough → impossible
            if (fuel < 0) {
                return -1;
            }
            
            // Add current station's fuel to the heap (if it's a real station)
            if (i < n) {
                maxHeap.offer(stations[i][1]);
                prevPos = currPos;
            }
        }
        
        return ans;
    }
}