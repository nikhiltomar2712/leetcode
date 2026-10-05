class Solution {
    public boolean areNumbersAscending(String s) {
        int prev = -1;
        for (String token : s.split(" ")) {
            if (Character.isDigit(token.charAt(0))) {
                int num = Integer.parseInt(token);
                if (num <= prev) return false;
                prev = num;
            }
        }
        return true;
    }
}