class Solution {
    public int numberOfMatches(int n) {
        int matches = 0;
        while (n > 1) {
            matches += n / 2;          // matches played this round
            n = (n + 1) / 2;           // teams advancing (ceil(n/2))
        }
        return matches;
    }
}