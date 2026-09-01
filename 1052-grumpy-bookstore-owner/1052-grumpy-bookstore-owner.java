class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int baseSatisfied = 0;   // customers already satisfied when owner is not grumpy
        int extra = 0;           // max extra customers we can save with the technique
        int windowExtra = 0;     // current window of extra customers

        // First, calculate the base satisfied customers
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                baseSatisfied += customers[i];
            }
        }

        // Sliding window of size 'minutes' to find the maximum extra customers we can save
        for (int i = 0; i < n; i++) {
            // Add current minute if owner was grumpy
            if (grumpy[i] == 1) {
                windowExtra += customers[i];
            }

            // Remove the leftmost minute when window exceeds 'minutes'
            if (i >= minutes && grumpy[i - minutes] == 1) {
                windowExtra -= customers[i - minutes];
            }

            // Update the maximum extra we can get
            extra = Math.max(extra, windowExtra);
        }

        return baseSatisfied + extra;
    }
}