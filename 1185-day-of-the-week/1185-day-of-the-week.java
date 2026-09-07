class Solution {
    public String dayOfTheWeek(int day, int month, int year) {
        String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", 
                         "Thursday", "Friday", "Saturday"};
        
        int[] daysInMonth = {31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 
                             31, 31, 30, 31, 30, 31};
        
        int totalDays = 0;
        
        // Add days from previous years (starting from 1971)
        for (int y = 1971; y < year; y++) {
            totalDays += isLeapYear(y) ? 366 : 365;
        }
        
        // Add days from previous months of the current year
        for (int m = 0; m < month - 1; m++) {
            totalDays += daysInMonth[m];
        }
        
        // Add the current day
        totalDays += day;
        
        // January 1, 1971 was a Friday → offset by 4
        // (because (totalDays + 4) % 7 maps correctly to the week array)
        return week[(totalDays + 4) % 7];
    }
    
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}