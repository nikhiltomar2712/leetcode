class Solution {
    public String maxValue(String n, int x) {
        StringBuilder sb = new StringBuilder();
        boolean negative = n.charAt(0) == '-';
        int start = negative ? 1 : 0;
        boolean inserted = false;
        for (int i = start; i < n.length(); i++) {
            char c = n.charAt(i);
            if (!inserted && ((negative && c - '0' > x) || (!negative && c - '0' < x))) {
                sb.append((char) ('0' + x));
                inserted = true;
            }
            sb.append(c);
        }
        if (!inserted) sb.append((char) ('0' + x));
        return negative ? "-" + sb : sb.toString();
    }
}