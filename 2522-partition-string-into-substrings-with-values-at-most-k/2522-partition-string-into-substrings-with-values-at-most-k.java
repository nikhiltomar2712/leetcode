class Solution {
    public int minimumPartition(String s, int k) {
        int count = 1;
        long cur = 0;
        for (char c : s.toCharArray()) {
            int d = c - '0';
            if (d > k) return -1;
            if (cur * 10 + d > k) {
                count++;
                cur = d;
            } else {
                cur = cur * 10 + d;
            }
        }
        return count;
    }
}