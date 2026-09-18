class Solution {
    public String reformatDate(String date) {
        String[] parts = date.split(" ");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        // Strip suffix (st, nd, rd, th)
        day = day.substring(0, day.length() - 2);

        String[] months = {"Jan","Feb","Mar","Apr","May","Jun",
                           "Jul","Aug","Sep","Oct","Nov","Dec"};
        int monthNum = 1;
        for (int i = 0; i < 12; i++) {
            if (months[i].equals(month)) {
                monthNum = i + 1;
                break;
            }
        }

        return String.format("%s-%02d-%02d", year, monthNum, Integer.parseInt(day));
    }
}