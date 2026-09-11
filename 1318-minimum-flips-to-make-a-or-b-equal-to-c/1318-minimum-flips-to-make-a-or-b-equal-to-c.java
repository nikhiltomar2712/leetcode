class Solution {
    public int minFlips(int a, int b, int c) {
        int flips = 0;
        
        // Process up to 32 bits (enough for int range)
        for (int i = 0; i < 32; i++) {
            int abit = (a >> i) & 1;
            int bbit = (b >> i) & 1;
            int cbit = (c >> i) & 1;
            
            if (cbit == 1) {
                // Need at least one of a, b to be 1
                if (abit == 0 && bbit == 0) {
                    flips += 1;
                }
            } else {
                // cbit == 0: both a and b must be 0
                flips += abit + bbit;
            }
        }
        
        return flips;
    }
}