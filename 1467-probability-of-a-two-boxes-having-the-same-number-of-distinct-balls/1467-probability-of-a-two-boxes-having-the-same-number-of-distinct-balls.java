class Solution {
    public double getProbability(int[] balls) {
        int n = balls.length;
        int total = 0;
        for (int b : balls) total += b;
        int half = total / 2;

        double[] fact = new double[total + 1];
        fact[0] = 0;
        for (int i = 1; i <= total; i++) {
            fact[i] = fact[i - 1] + Math.log(i);
        }

        double[] result = new double[2];
        dfs(balls, 0, 0, 0, 0, 0, half, fact, result, 1.0, 0);

        return result[1] / result[0];
    }

    private void dfs(int[] balls, int idx, int countA, int countB, int distinctA, int distinctB,
                     int half, double[] fact, double[] result, double prob, int totalWays) {
        if (countA > half || countB > half) return;

        if (idx == balls.length) {
            if (countA == half && distinctA == distinctB) {
                result[1] += prob;
            }
            result[0] += prob;
            return;
        }

        int b = balls[idx];
        for (int i = 0; i <= b; i++) {
            double comb = Math.exp(fact[b] - fact[i] - fact[b - i]);
            int newDistinctA = distinctA + (i > 0 ? 1 : 0);
            int newDistinctB = distinctB + (i < b ? 1 : 0);
            dfs(balls, idx + 1, countA + i, countB + b - i,
                newDistinctA, newDistinctB, half, fact, result,
                prob * comb, totalWays);
        }
    }
}