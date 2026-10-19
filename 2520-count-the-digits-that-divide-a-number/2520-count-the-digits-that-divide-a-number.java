class Solution {
    public int countDigits(int num) {
        int count = 0;
        int temp = num;
        while (temp > 0) {
            int d = temp % 10;
            if (d != 0 && num % d == 0) count++;
            temp /= 10;
        }
        return count;
    }
}