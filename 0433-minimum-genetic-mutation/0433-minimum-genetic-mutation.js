/**
 * @param {string} startGene
 * @param {string} endGene
 * @param {string[]} bank
 * @return {number}
 */
var minMutation = function(startGene, endGene, bank) {
    // If endGene is not in bank, it's impossible to reach
    const bankSet = new Set(bank);
    if (!bankSet.has(endGene)) return -1;
    
    // BFS queue: [gene, mutationsCount]
    const queue = [[startGene, 0]];
    const visited = new Set([startGene]);
    
    // Possible gene characters
    const genes = ['A', 'C', 'G', 'T'];
    
    while (queue.length > 0) {
        const [current, mutations] = queue.shift();
        
        // If we reached the endGene, return the number of mutations
        if (current === endGene) return mutations;
        
        // Try all possible mutations (change each position to A, C, G, T)
        for (let i = 0; i < 8; i++) {
            for (const gene of genes) {
                // Skip if it's the same character
                if (current[i] === gene) continue;
                
                // Create new gene string
                const newGene = current.slice(0, i) + gene + current.slice(i + 1);
                
                // Check if it's valid (in bank) and not visited
                if (bankSet.has(newGene) && !visited.has(newGene)) {
                    visited.add(newGene);
                    queue.push([newGene, mutations + 1]);
                }
            }
        }
    }
    
    // No valid path found
    return -1;
};