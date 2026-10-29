class Solution {
    public int countSeniors(String[] details) {
        int count = 0;
        for (String d : details) {
            int age = (d.charAt(11) - '0') * 10 + (d.charAt(12) - '0');
            if (age > 60) count++;
        }
        return count;
    }
}