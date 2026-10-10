class Solution {
    public double calculateTax(int[][] brackets, int income) {
        double tax = 0;
        int prev = 0;
        for (int[] b : brackets) {
            int upper = b[0], rate = b[1];
            if (income > prev) {
                int taxable = Math.min(income, upper) - prev;
                tax += taxable * rate / 100.0;
                prev = upper;
            } else {
                break;
            }
        }
        return tax;
    }
}