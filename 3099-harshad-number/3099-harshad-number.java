class Solution {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sum = 0;
        int n = x;

        // Compute the sum of digits
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        // If x is divisible by the digit sum, it's a Harshad number
        return (x % sum == 0) ? sum : -1;
    }
}