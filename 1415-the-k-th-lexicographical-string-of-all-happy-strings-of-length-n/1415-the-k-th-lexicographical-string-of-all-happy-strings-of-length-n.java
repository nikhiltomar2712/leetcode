class Solution {
    public String getHappyString(int n, int k) {
        StringBuilder sb = new StringBuilder();

        for (int pos = 0; pos < n; pos++) {
            for (char c = 'a'; c <= 'c'; c++) {
                if (pos > 0 && c == sb.charAt(pos - 1)) continue;

                int remaining = n - pos - 1;
                int count = 1 << remaining; // 2^remaining

                if (k > count) {
                    k -= count;
                } else {
                    sb.append(c);
                    break;
                }
            }
            if (sb.length() != pos + 1) {
                // no valid character found
                return "";
            }
        }

        return sb.toString();
    }
}