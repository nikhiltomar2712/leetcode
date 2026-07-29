class Solution {
    public int repeatedStringMatch(String a, String b) {
        int m = a.length(), n = b.length();
        int minRepeats = (n + m - 1) / m;   // ceil(n / m)

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minRepeats; i++) {
            sb.append(a);
        }

        // Check minRepeats and minRepeats + 1
        for (int i = 0; i < 2; i++) {
            if (sb.toString().contains(b)) {
                return minRepeats + i;
            }
            sb.append(a);
        }

        return -1;
    }
}