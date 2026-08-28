class Solution {
    public int clumsy(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 6;
        if (n == 4) return 7;

        // n >= 5
        int mod = n % 4;
        if (mod == 0) return n + 1;
        if (mod == 1 || mod == 2) return n + 2;
        return n - 1; // mod == 3
    }
}
