class Solution {
    public int minTimeToType(String word) {
        int time = 0;
        char cur = 'a';
        for (char c : word.toCharArray()) {
            int diff = Math.abs(c - cur);
            time += Math.min(diff, 26 - diff) + 1;
            cur = c;
        }
        return time;
    }
}