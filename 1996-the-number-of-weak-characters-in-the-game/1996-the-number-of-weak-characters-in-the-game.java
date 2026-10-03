class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0];
            return a[1] - b[1];
        });
        int maxDef = 0;
        int count = 0;
        for (int[] p : properties) {
            if (p[1] < maxDef) {
                count++;
            } else {
                maxDef = p[1];
            }
        }
        return count;
    }
}