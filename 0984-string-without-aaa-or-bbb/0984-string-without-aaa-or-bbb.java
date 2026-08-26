class Solution {
    public String strWithout3a3b(int a, int b) {
        StringBuilder sb = new StringBuilder();

        while (a > 0 && b > 0) {
            if (a > b) {
                sb.append("aab");
                a -= 2;
                b -= 1;
            } else if (b > a) {
                sb.append("bba");
                a -= 1;
                b -= 2;
            } else { // a == b
                sb.append("ab");
                a--;
                b--;
            }
        }

        // Append any remaining characters (at most 2 of the same letter)
        while (a-- > 0) {
            sb.append('a');
        }
        while (b-- > 0) {
            sb.append('b');
        }

        return sb.toString();
    }
}