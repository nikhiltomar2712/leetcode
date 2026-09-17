class Solution {
    public List<String> simplifiedFractions(int n) {
        List<String> result = new ArrayList<>();

        for (int den = 2; den <= n; den++) {
            for (int num = 1; num < den; num++) {
                if (gcd(num, den) == 1) {
                    result.add(num + "/" + den);
                }
            }
        }

        return result;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}