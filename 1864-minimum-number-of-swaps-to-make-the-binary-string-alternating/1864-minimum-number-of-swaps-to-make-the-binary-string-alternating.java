class Solution {
    public int minSwaps(String s) {
        int n = s.length();
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }
        int zeros = n - ones;
        if (Math.abs(ones - zeros) > 1) return -1;
        if (ones > zeros) {
            return count(s, '1');
        } else if (zeros > ones) {
            return count(s, '0');
        } else {
            return Math.min(count(s, '1'), count(s, '0'));
        }
    }

    private int count(String s, char start) {
        int mismatches = 0;
        char expected = start;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != expected) mismatches++;
            expected = expected == '0' ? '1' : '0';
        }
        return mismatches / 2;
    }
}
