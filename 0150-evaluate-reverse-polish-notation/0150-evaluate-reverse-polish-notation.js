/**
 * @param {string[]} tokens
 * @return {number}
 */
var evalRPN = function(tokens) {
    const stack = [];
    
    for (const token of tokens) {
        if (isOperator(token)) {
            const b = stack.pop();
            const a = stack.pop();
            const result = applyOperator(a, b, token);
            stack.push(result);
        } else {
            stack.push(parseInt(token));
        }
    }
    
    return stack[0];
};

function isOperator(token) {
    return token === '+' || token === '-' || token === '*' || token === '/';
}

function applyOperator(a, b, operator) {
    switch (operator) {
        case '+': return a + b;
        case '-': return a - b;
        case '*': return a * b;
        case '/': return Math.trunc(a / b); // Truncates toward zero
        default: return 0;
    }
}