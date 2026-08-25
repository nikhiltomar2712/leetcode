/**
 * @param {string} preorder
 * @return {boolean}
 */
var isValidSerialization = function(preorder) {
    const nodes = preorder.split(',');
    let slots = 1; // Start with 1 slot for the root
    
    for (const node of nodes) {
        // Consume one slot for the current node
        slots--;
        
        // If slots become negative, it's invalid
        if (slots < 0) return false;
        
        // If it's not a null node, it creates 2 new slots (left and right children)
        if (node !== '#') {
            slots += 2;
        }
    }
    
    // All slots should be exactly filled
    return slots === 0;
};