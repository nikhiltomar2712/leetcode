class Solution {
    public String solveEquation(String equation) {
        String[] parts = equation.split("=");
        int[] left = parse(parts[0]);
        int[] right = parse(parts[1]);

        // Bring everything to left side: (coefX) * x + const = 0
        int coefX = left[0] - right[0];
        int constant = left[1] - right[1];

        if (coefX == 0) {
            return constant == 0 ? "Infinite solutions" : "No solution";
        }

        // x = -constant / coefX
        return "x=" + (-constant / coefX);
    }

    // Returns [coefficient of x, constant term]
    private int[] parse(String expr) {
        int coef = 0;
        int constant = 0;
        int n = expr.length();
        int i = 0;

        while (i < n) {
            int sign = 1;
            if (expr.charAt(i) == '+') {
                i++;
            } else if (expr.charAt(i) == '-') {
                sign = -1;
                i++;
            }

            int num = 0;
            boolean hasNum = false;
            while (i < n && Character.isDigit(expr.charAt(i))) {
                num = num * 10 + (expr.charAt(i) - '0');
                hasNum = true;
                i++;
            }

            if (i < n && expr.charAt(i) == 'x') {
                // term with x
                coef += sign * (hasNum ? num : 1);
                i++;
            } else {
                // constant term
                constant += sign * num;
            }
        }

        return new int[]{coef, constant};
    }
}