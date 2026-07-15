/**
 * @param {number[]} stones
 * @return {boolean}
 */
var canCross = function(stones) {
    // If the gap between first and second stone is not 1, frog can't start
    if (stones[1] - stones[0] !== 1) return false;
    
    // Map each stone position to a Set of possible jump sizes to reach it
    const dp = new Map();
    for (let stone of stones) {
        dp.set(stone, new Set());
    }
    dp.get(0).add(0); // Starting position with jump 0
    
    for (let stone of stones) {
        const jumps = dp.get(stone);
        for (let jump of jumps) {
            // Try all three possible next jumps
            for (let nextJump of [jump - 1, jump, jump + 1]) {
                if (nextJump > 0) {
                    const nextStone = stone + nextJump;
                    if (dp.has(nextStone)) {
                        dp.get(nextStone).add(nextJump);
                    }
                }
            }
        }
    }
    
    return dp.get(stones[stones.length - 1]).size > 0;
};