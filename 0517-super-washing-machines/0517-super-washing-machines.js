/**
 * @param {number[]} machines
 * @return {number}
 */
var findMinMoves = function(machines) {
    const n = machines.length;
    let total = 0;
    for (let dresses of machines) {
        total += dresses;
    }
    
    if (total % n !== 0) {
        return -1;
    }
    
    const avg = Math.floor(total / n);
    let ans = 0;
    let cumulative = 0;
    
    for (let dresses of machines) {
        const diff = dresses - avg;
        cumulative += diff;
        ans = Math.max(ans, Math.max(Math.abs(cumulative), diff));
    }
    
    return ans;
};