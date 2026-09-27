class Solution {
    public int minimumBoxes(int n) {
        long total = 0;
        long level = 0;
        long base = 0;

        while (total + base + level + 1 <= n) {
            level++;
            base += level;
            total += base;
        }

        long remaining = n - total;

        long extra = 0;
        long sum = 0;
        while (sum < remaining) {
            extra++;
            sum += extra;
        }

        return (int) (base + extra);
    }
}