int memo[50][50][50];
int N;
int** G;

int dfs(int r1, int c1, int c2) {
    int r2 = r1 + c1 - c2;
    
    // Out of bounds or thorn
    if (r1 >= N || c1 >= N || r2 >= N || c2 >= N ||
        G[r1][c1] == -1 || G[r2][c2] == -1) {
        return -1e9;
    }
    
    // Reached bottom-right
    if (r1 == N - 1 && c1 == N - 1) {
        return G[r1][c1];
    }
    
    if (memo[r1][c1][c2] != -1) {
        return memo[r1][c1][c2];
    }
    
    int cherries = G[r1][c1];
    if (r1 != r2 || c1 != c2) {
        cherries += G[r2][c2];
    }
    
    // 4 possible moves for the two persons
    int best = dfs(r1 + 1, c1, c2);       // down, down
    best = best > dfs(r1 + 1, c1, c2 + 1) ? best : dfs(r1 + 1, c1, c2 + 1); // down, right
    best = best > dfs(r1, c1 + 1, c2)     ? best : dfs(r1, c1 + 1, c2);     // right, down
    best = best > dfs(r1, c1 + 1, c2 + 1) ? best : dfs(r1, c1 + 1, c2 + 1); // right, right
    
    memo[r1][c1][c2] = cherries + best;
    return memo[r1][c1][c2];
}

int cherryPickup(int** grid, int gridSize, int* gridColSize) {
    N = gridSize;
    G = grid;
    memset(memo, -1, sizeof(memo));
    
    int ans = dfs(0, 0, 0);
    return ans < 0 ? 0 : ans;
}