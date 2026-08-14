class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n == 1) return 1;
        
        // Create array of cars and sort by position (closest to target first)
        Car[] cars = new Car[n];
        for (int i = 0; i < n; i++) {
            cars[i] = new Car(position[i], (double)(target - position[i]) / speed[i]);
        }
        Arrays.sort(cars, (a, b) -> Integer.compare(b.position, a.position));
        
        int fleets = 0;
        double currentMaxTime = 0;
        
        // Process cars from closest to target to farthest
        for (Car car : cars) {
            // If this car takes longer to reach target than the current fleet,
            // it cannot catch up, so it forms a new fleet
            if (car.time > currentMaxTime) {
                fleets++;
                currentMaxTime = car.time;
            }
            // Otherwise, it catches up to the fleet ahead (no new fleet)
        }
        
        return fleets;
    }
    
    private static class Car {
        int position;
        double time;
        
        Car(int position, double time) {
            this.position = position;
            this.time = time;
        }
    }
}