class Solution {
    public double nthPersonGetsNthSeat(int n) {
        // Base case: with only 1 passenger, they get their own seat
        if (n == 1) {
            return 1.0;
        }
        // For n >= 2, the probability is always 0.5
        return 0.5;
    }
}