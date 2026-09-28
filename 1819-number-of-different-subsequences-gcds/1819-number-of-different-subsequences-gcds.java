class Solution {
    public int countDifferentSubsequenceGCDs(int[] nums) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        boolean[] present = new boolean[max + 1];
        for (int num : nums) present[num] = true;
        int count = 0;
        for (int g = 1; g <= max; g++) {
            int gcd = 0;
            for (int multiple = g; multiple <= max; multiple += g) {
                if (present[multiple]) {
                    gcd = gcd(gcd, multiple);
                    if (gcd == g) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}