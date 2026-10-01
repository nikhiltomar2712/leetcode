class Solution {
    public boolean isThree(int n) {
        int count = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (i * i == n) count++;
                else count += 2;
            }
            if (count > 3) return false;
        }
        return count == 3;
    }
}