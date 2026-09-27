class Solution {
    public String maximumTime(String time) {
        char[] t = time.toCharArray();

        // Hour tens place
        if (t[0] == '?') {
            t[0] = (t[1] == '?' || t[1] <= '3') ? '2' : '1';
        }
        // Hour ones place
        if (t[1] == '?') {
            t[1] = (t[0] == '2') ? '3' : '9';
        }
        // Minute tens place
        if (t[3] == '?') {
            t[3] = '5';
        }
        // Minute ones place
        if (t[4] == '?') {
            t[4] = '9';
        }

        return new String(t);
    }
}