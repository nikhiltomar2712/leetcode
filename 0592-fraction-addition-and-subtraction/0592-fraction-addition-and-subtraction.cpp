class Solution {
public:
    string fractionAddition(string expression) {
        int num = 0, den = 1; // current result 0/1
        int i = 0, n = expression.size();
        while (i < n) {
            int sign = 1;
            if (expression[i] == '+' || expression[i] == '-') {
                sign = expression[i++] == '+' ? 1 : -1;
            }
            int a = 0;
            while (i < n && isdigit(expression[i])) {
                a = a * 10 + (expression[i++] - '0');
            }
            i++; // skip '/'
            int b = 0;
            while (i < n && isdigit(expression[i])) {
                b = b * 10 + (expression[i++] - '0');
            }
            // add a/b * sign to num/den
            num = num * b + sign * a * den;
            den = den * b;
            int g = gcd(abs(num), den);
            num /= g;
            den /= g;
        }
        if (den < 0) {
            num = -num;
            den = -den;
        }
        return to_string(num) + "/" + to_string(den);
    }
private:
    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
};