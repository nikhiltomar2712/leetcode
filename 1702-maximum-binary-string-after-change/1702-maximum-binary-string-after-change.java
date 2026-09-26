class Solution {
    public String maximumBinaryString(String binary) {
        int n = binary.length();
        int zeros = 0;
        int firstZero = n;
        for (int i = 0; i < n; i++) {
            if (binary.charAt(i) == '0') {
                zeros++;
                if (firstZero == n) firstZero = i;
            }
        }

        // No zeros, or only one zero → already maximal
        if (zeros <= 1) return binary;

        char[] res = new char[n];
        // Everything is '1' except the position (firstZero + zeros - 1)
        java.util.Arrays.fill(res, '1');
        res[firstZero + zeros - 1] = '0';
        return new String(res);
    }
}