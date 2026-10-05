class Solution {
    public int nextBeautifulNumber(int n) {
        for (int i = n + 1; ; i++) {
            if (isBeautiful(i)) return i;
        }
    }

    private boolean isBeautiful(int num) {
        int[] count = new int[10];
        int temp = num;
        while (temp > 0) {
            count[temp % 10]++;
            temp /= 10;
        }
        for (int d = 0; d <= 9; d++) {
            if (count[d] > 0 && count[d] != d) return false;
        }
        return true;
    }
}