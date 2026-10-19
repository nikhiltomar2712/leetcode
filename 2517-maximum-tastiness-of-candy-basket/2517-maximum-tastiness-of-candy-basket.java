class Solution {
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int left = 0, right = price[price.length - 1] - price[0];
        while (left < right) {
            int mid = (left + right + 1) / 2;
            int count = 1, last = price[0];
            for (int i = 1; i < price.length; i++) {
                if (price[i] - last >= mid) {
                    count++;
                    last = price[i];
                }
            }
            if (count >= k) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}