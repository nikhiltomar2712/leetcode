class Solution {
    public int countBalls(int lowLimit, int highLimit) {
        int[] boxes = new int[46]; // max digit sum for 1..100000 is 45
        int max = 0;

        for (int i = lowLimit; i <= highLimit; i++) {
            int sum = 0, n = i;
            while (n > 0) {
                sum += n % 10;
                n /= 10;
            }
            boxes[sum]++;
            max = Math.max(max, boxes[sum]);
        }

        return max;
    }
}