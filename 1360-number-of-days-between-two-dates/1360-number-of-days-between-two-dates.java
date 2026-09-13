class Solution {
    public int daysBetweenDates(String date1, String date2) {
        return Math.abs(daysFromEpoch(date1) - daysFromEpoch(date2));
    }

    // Days from 1971-01-01 to the given date
    private int daysFromEpoch(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));

        int total = 0;

        // Sum days for all years from 1971 to year - 1
        for (int y = 1971; y < year; y++) {
            total += isLeap(y) ? 366 : 365;
        }

        // Days in each month of a non-leap year
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Sum days for months before the current month
        for (int m = 1; m < month; m++) {
            total += daysInMonth[m - 1];
            // Add extra day for February in a leap year
            if (m == 2 && isLeap(year)) {
                total += 1;
            }
        }

        // Add the current day
        total += day;

        return total;
    }

    private boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}