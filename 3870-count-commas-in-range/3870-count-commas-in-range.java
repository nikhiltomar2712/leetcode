class Solution {
    public int countCommas(int n) {
        // Numbers from 1 to 999 have no commas
        if (n < 1000) {
            return 0;
        }
        
        // For numbers 1000 to 100,000, each has exactly 1 comma
        // Total commas = n - 999 (because numbers 1000 through n)
        return n - 999;
    }
}