class Solution {
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        if (n < k) return false;

        Set<String> seen = new HashSet<>();
        int total = 1 << k;

        for (int i = 0; i + k <= n; i++) {
            seen.add(s.substring(i, i + k));
            if (seen.size() == total) return true;
        }

        return seen.size() == total;
    }
}