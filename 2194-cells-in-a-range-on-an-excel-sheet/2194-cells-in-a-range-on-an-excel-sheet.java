class Solution {
    public List<String> cellsInRange(String s) {
        char c1 = s.charAt(0), c2 = s.charAt(3);
        char r1 = s.charAt(1), r2 = s.charAt(4);
        List<String> res = new ArrayList<>();
        for (char c = c1; c <= c2; c++) {
            for (char r = r1; r <= r2; r++) {
                res.add("" + c + r);
            }
        }
        return res;
    }
}