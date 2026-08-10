class Solution {
    public int numFriendRequests(int[] ages) {
        // Count frequency of each age (1 to 120)
        int[] ageCount = new int[121];
        for (int age : ages) {
            ageCount[age]++;
        }
        
        int totalRequests = 0;
        
        // For each possible age x and age y
        for (int x = 1; x <= 120; x++) {
            if (ageCount[x] == 0) continue;
            
            // People of age x can request to people of age y where:
            // y > 0.5*x + 7 AND y <= x (from condition 2)
            // Also handle the 100+ edge case
            int minY = (int)(0.5 * x + 7) + 1; // y must be > 0.5*x + 7
            int maxY = x; // y cannot be > x
            
            // Special case: if x >= 100, y can be > x (100 can request to older)
            // But condition 2 says y > x is not allowed, so maxY remains x
            // Actually condition 3 only prevents x<100 from requesting y>100
            // So for x>=100, y can be >100, but condition 2 still prevents y>x
            // Therefore maxY is always x
            // Wait - let's re-evaluate: condition 2 is age[y] > age[x] -> no request
            // So y must be <= x always
            
            for (int y = minY; y <= maxY && y <= 120; y++) {
                if (ageCount[y] == 0) continue;
                
                if (x == y) {
                    // A person can't request themselves
                    // But people of same age can request each other
                    // Each person sends request to all others of same age
                    totalRequests += ageCount[x] * (ageCount[x] - 1);
                } else {
                    totalRequests += ageCount[x] * ageCount[y];
                }
            }
        }
        
        return totalRequests;
    }
}