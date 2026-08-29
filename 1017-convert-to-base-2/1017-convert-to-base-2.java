class Solution {
    public String baseNeg2(int n) {
        if (n == 0) return "0";

        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            // remainder is always 0 or 1
            int remainder = n & 1;
            sb.append(remainder);
            // divide by -2 (with correct rounding)
            n = -(n >> 1);
        }
        return sb.reverse().toString();
    }
}