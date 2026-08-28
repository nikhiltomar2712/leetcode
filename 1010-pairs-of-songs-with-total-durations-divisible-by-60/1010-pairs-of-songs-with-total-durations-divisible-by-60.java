class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] count = new int[60];
        int ans = 0;
        
        for (int t : time) {
            int x = t % 60;
            int y = (60 - x) % 60;   // needed complement
            ans += count[y];
            count[x]++;
        }
        
        return ans;
    }
}