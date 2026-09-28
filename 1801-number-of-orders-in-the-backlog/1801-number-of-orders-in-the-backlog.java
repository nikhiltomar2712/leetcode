class Solution {
    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> buy = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> sell = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] order : orders) {
            int price = order[0], amount = order[1], type = order[2];
            if (type == 0) {
                while (amount > 0 && !sell.isEmpty() && sell.peek()[0] <= price) {
                    int[] top = sell.peek();
                    int trade = Math.min(amount, top[1]);
                    amount -= trade;
                    top[1] -= trade;
                    if (top[1] == 0) sell.poll();
                }
                if (amount > 0) buy.offer(new int[]{price, amount});
            } else {
                while (amount > 0 && !buy.isEmpty() && buy.peek()[0] >= price) {
                    int[] top = buy.peek();
                    int trade = Math.min(amount, top[1]);
                    amount -= trade;
                    top[1] -= trade;
                    if (top[1] == 0) buy.poll();
                }
                if (amount > 0) sell.offer(new int[]{price, amount});
            }
        }
        long total = 0;
        int mod = 1000000007;
        for (int[] b : buy) total += b[1];
        for (int[] s : sell) total += s[1];
        return (int) (total % mod);
    }
}