var catMouseGame = function(graph) {
    const n = graph.length;
    const MOUSE_WIN = 1, CAT_WIN = 2, DRAW = 0;
    const MOUSE_TURN = 0, CAT_TURN = 1;
    
    // result[mouse][cat][turn]
    const result = Array.from({length: n}, () => 
        Array.from({length: n}, () => [0, 0])
    );
    
    // degree[mouse][cat][turn] = number of possible next states
    const degree = Array.from({length: n}, () => 
        Array.from({length: n}, () => [0, 0])
    );
    
    // Calculate out-degree for each state
    for (let mouse = 0; mouse < n; mouse++) {
        for (let cat = 0; cat < n; cat++) {
            // Mouse's turn: can move to any neighbor
            degree[mouse][cat][MOUSE_TURN] = graph[mouse].length;
            
            // Cat's turn: can move to any neighbor except hole (0)
            degree[mouse][cat][CAT_TURN] = graph[cat].filter(next => next !== 0).length;
        }
    }
    
    const queue = [];
    
    // Initialize terminal states
    for (let cat = 0; cat < n; cat++) {
        for (let turn = 0; turn < 2; turn++) {
            // Mouse reached hole - Mouse wins
            result[0][cat][turn] = MOUSE_WIN;
            queue.push([0, cat, turn, MOUSE_WIN]);
        }
    }
    
    // Cat catches mouse (cat === mouse, but cat !== 0)
    for (let pos = 1; pos < n; pos++) {
        for (let turn = 0; turn < 2; turn++) {
            result[pos][pos][turn] = CAT_WIN;
            queue.push([pos, pos, turn, CAT_WIN]);
        }
    }
    
    // Process queue
    while (queue.length) {
        const [mouse, cat, turn, winner] = queue.shift();
        
        if (turn === MOUSE_TURN) {
            // Current player is Mouse, so previous player was Cat
            // Looking for previous states where Cat moved to current cat position
            for (const prevCat of graph[cat]) {
                if (prevCat === 0) continue;  // Cat cannot be at hole
                if (result[mouse][prevCat][CAT_TURN] !== DRAW) continue;
                
                if (winner === MOUSE_WIN) {
                    // Mouse wins from this state, so Cat should avoid moving here
                    // But if Cat moves here, Mouse wins => Cat loses
                    degree[mouse][prevCat][CAT_TURN]--;
                    if (degree[mouse][prevCat][CAT_TURN] === 0) {
                        // All moves from prevCat lead to Mouse win
                        result[mouse][prevCat][CAT_TURN] = MOUSE_WIN;
                        queue.push([mouse, prevCat, CAT_TURN, MOUSE_WIN]);
                    }
                } else if (winner === CAT_WIN) {
                    // Cat can win by moving here
                    result[mouse][prevCat][CAT_TURN] = CAT_WIN;
                    queue.push([mouse, prevCat, CAT_TURN, CAT_WIN]);
                }
            }
        } else {
            // Current player is Cat, so previous player was Mouse
            // Looking for previous states where Mouse moved to current mouse position
            for (const prevMouse of graph[mouse]) {
                if (result[prevMouse][cat][MOUSE_TURN] !== DRAW) continue;
                
                if (winner === CAT_WIN) {
                    // Cat wins from this state, so Mouse should avoid moving here
                    degree[prevMouse][cat][MOUSE_TURN]--;
                    if (degree[prevMouse][cat][MOUSE_TURN] === 0) {
                        // All moves from prevMouse lead to Cat win
                        result[prevMouse][cat][MOUSE_TURN] = CAT_WIN;
                        queue.push([prevMouse, cat, MOUSE_TURN, CAT_WIN]);
                    }
                } else if (winner === MOUSE_WIN) {
                    // Mouse can win by moving here
                    result[prevMouse][cat][MOUSE_TURN] = MOUSE_WIN;
                    queue.push([prevMouse, cat, MOUSE_TURN, MOUSE_WIN]);
                }
            }
        }
    }
    
    return result[1][2][MOUSE_TURN];
};