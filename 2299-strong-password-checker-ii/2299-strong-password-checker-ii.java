class Solution {
    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) return false;
        boolean lower = false, upper = false, digit = false, special = false;
        String specials = "!@#$%^&*()-+";
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isLowerCase(c)) lower = true;
            else if (Character.isUpperCase(c)) upper = true;
            else if (Character.isDigit(c)) digit = true;
            else if (specials.indexOf(c) != -1) special = true;
            if (i > 0 && c == password.charAt(i - 1)) return false;
        }
        return lower && upper && digit && special;
    }
}