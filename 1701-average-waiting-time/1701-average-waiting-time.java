class Solution {
    public double averageWaitingTime(int[][] customers) {
        long currentTime = 0;   // when the chef becomes free
        long totalWait = 0;

        for (int[] c : customers) {
            int arrival = c[0];
            int prep = c[1];

            // Chef starts at max(arrival, currentTime)
            long start = Math.max(currentTime, arrival);
            long finish = start + prep;

            totalWait += finish - arrival;
            currentTime = finish;
        }

        return (double) totalWait / customers.length;
    }
}