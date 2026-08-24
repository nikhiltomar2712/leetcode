class Solution {
    // ratios[i] = 1 / (10^i - 1)   for a repeating block of length i
    private static final double[] ratios = {
        1.0,           // length 0 (not used)
        1.0 / 9,       // length 1  → 0.1111...
        1.0 / 99,      // length 2  → 0.010101...
        1.0 / 999,     // length 3
        1.0 / 9999     // length 4
    };

    public boolean isRationalEqual(String s, String t) {
        return Math.abs(valueOf(s) - valueOf(t)) < 1e-9;
    }

    private double valueOf(String s) {
        // No repeating part → just parse as a normal double
        if (!s.contains("(")) {
            return Double.parseDouble(s);
        }

        int leftParen  = s.indexOf('(');
        int rightParen = s.indexOf(')');
        int dot        = s.indexOf('.');

        // Everything before the opening parenthesis
        // (IntegerPart + NonRepeatingPart)
        double nonRepeating = Double.parseDouble(s.substring(0, leftParen));
        int nonRepeatingLength = leftParen - dot - 1;   // digits after the dot

        // The repeating block
        int repeating = Integer.parseInt(s.substring(leftParen + 1, rightParen));
        int repeatingLength = rightParen - leftParen - 1;

        // Add the contribution of the infinite repeating part
        return nonRepeating
             + repeating * Math.pow(0.1, nonRepeatingLength) * ratios[repeatingLength];
    }
}