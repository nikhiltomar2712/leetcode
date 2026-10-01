class Solution {
    public int numberOfRounds(String loginTime, String logoutTime) {
        int start = toMinutes(loginTime);
        int end = toMinutes(logoutTime);
        if (end < start) end += 1440;
        start = (start + 14) / 15;
        end = end / 15;
        return Math.max(0, end - start);
    }

    private int toMinutes(String time) {
        int h = Integer.parseInt(time.substring(0, 2));
        int m = Integer.parseInt(time.substring(3, 5));
        return h * 60 + m;
    }
}