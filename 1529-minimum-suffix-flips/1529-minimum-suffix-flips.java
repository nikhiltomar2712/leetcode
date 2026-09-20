class Solution {
    public int minFlips(String target) {
        int flips = 0;
        char prev = '0';
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) != prev) {
                flips++;
                prev = target.charAt(i);
            }
        }
        return flips;
    }
}