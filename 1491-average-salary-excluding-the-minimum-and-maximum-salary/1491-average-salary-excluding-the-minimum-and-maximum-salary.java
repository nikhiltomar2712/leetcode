class Solution {
    public double average(int[] salary) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        long sum = 0;

        for (int s : salary) {
            min = Math.min(min, s);
            max = Math.max(max, s);
            sum += s;
        }

        // Remove one min and one max (values are unique)
        sum -= (min + max);
        return (double) sum / (salary.length - 2);
    }
}