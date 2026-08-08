import java.util.*;

class Solution {
    public List<String> ambiguousCoordinates(String s) {
        List<String> ans = new ArrayList<>();
        // remove the outer parentheses
        String digits = s.substring(1, s.length() - 1);
        int n = digits.length();

        // try every split position
        for (int i = 1; i < n; i++) {
            List<String> lefts  = make(digits.substring(0, i));
            List<String> rights = make(digits.substring(i));
            for (String x : lefts) {
                for (String y : rights) {
                    ans.add("(" + x + ", " + y + ")");
                }
            }
        }
        return ans;
    }

    // generate all valid numbers from a pure digit string
    private List<String> make(String t) {
        List<String> res = new ArrayList<>();
        int len = t.length();
        for (int d = 1; d <= len; d++) {          // decimal after the first d characters
            String left  = t.substring(0, d);
            String right = t.substring(d);

            boolean leftOk  = left.equals("0") || !left.startsWith("0");
            boolean rightOk = right.isEmpty() || !right.endsWith("0");

            if (leftOk && rightOk) {
                res.add(right.isEmpty() ? left : left + "." + right);
            }
        }
        return res;
    }
}