var reverse = function(x) {
    const sign = x < 0 ? -1 : 1;
    const reversedStr = Math.abs(x).toString().split('').reverse().join('');
    const result = sign * Number(reversedStr);
    
    const MAX = Math.pow(2, 31) - 1;
    const MIN = -Math.pow(2, 31);
    
    if (result > MAX || result < MIN) {
        return 0;
    }
    
    return result;
};