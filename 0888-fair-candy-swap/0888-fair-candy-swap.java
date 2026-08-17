class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        // Calculate total candies for Alice and Bob
        int sumAlice = 0;
        int sumBob = 0;
        Set<Integer> bobSet = new HashSet<>();
        
        for (int candy : aliceSizes) {
            sumAlice += candy;
        }
        
        for (int candy : bobSizes) {
            sumBob += candy;
            bobSet.add(candy); // Store Bob's candy box sizes for quick lookup
        }
        
        // The difference that needs to be balanced
        // After exchange: sumAlice - a + b = sumBob - b + a
        // => sumAlice - sumBob = 2a - 2b
        // => a - b = (sumAlice - sumBob) / 2
        int diff = (sumAlice - sumBob) / 2;
        
        // Find a pair (a from Alice, b from Bob) such that a - b = diff
        for (int a : aliceSizes) {
            int b = a - diff;
            if (bobSet.contains(b)) {
                return new int[]{a, b};
            }
        }
        
        return new int[0]; // Should never reach here as guaranteed answer exists
    }
}