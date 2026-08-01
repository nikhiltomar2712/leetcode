int reachNumber(int target) {
    target = target < 0 ? -target : target;   // absolute value
    
    int steps = 0;
    int sum = 0;
    
    // Keep adding until sum >= target and (sum - target) is even
    while (sum < target || (sum - target) % 2 != 0) {
        steps++;
        sum += steps;
    }
    
    return steps;
}