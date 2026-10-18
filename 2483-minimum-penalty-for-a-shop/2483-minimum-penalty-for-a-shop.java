class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int totalY = 0;
        for (char c : customers.toCharArray()) {
            if (c == 'Y') totalY++;
        }
        int minPenalty = totalY;
        int bestHour = 0;
        int penalty = totalY;
        for (int i = 0; i < n; i++) {
            if (customers.charAt(i) == 'Y') {
                penalty--;
            } else {
                penalty++;
            }
            if (penalty < minPenalty) {
                minPenalty = penalty;
                bestHour = i + 1;
            }
        }
        return bestHour;
    }
}