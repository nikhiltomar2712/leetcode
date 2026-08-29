class Solution {
    public boolean queryString(String s, int n) {
        // We only need to check the upper half of the range
        for (int i = n; i > n / 2; i--) {
            String binary = Integer.toBinaryString(i);
            if (!s.contains(binary)) {
                return false;
            }
        }
        return true;
    }
}