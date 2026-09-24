class Solution {
    public String kthSmallestPath(int[] destination, int k) {
        int v = destination[0];  // vertical moves
        int h = destination[1];  // horizontal moves
        int total = v + h;       // ← capture before decrementing
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < total; i++) {
            if (h == 0) {
                sb.append('V');
            } else if (v == 0) {
                sb.append('H');
            } else {
                long pathsStartingWithH = combination(h + v - 1, v);
                if (k <= pathsStartingWithH) {
                    sb.append('H');
                    h--;
                } else {
                    sb.append('V');
                    k -= pathsStartingWithH;
                    v--;
                }
            }
        }

        return sb.toString();
    }

    private long combination(int n, int r) {
        if (r > n - r) r = n - r;
        long result = 1;
        for (int i = 1; i <= r; i++) {
            result = result * (n - r + i) / i;
        }
        return result;
    }
}