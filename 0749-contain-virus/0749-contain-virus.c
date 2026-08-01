int containVirus(int** isInfected, int isInfectedSize, int* isInfectedColSize) {
    int m = isInfectedSize;
    int n = isInfectedColSize[0];
    int dirs[4][2] = {{-1,0},{1,0},{0,-1},{0,1}};
    int ans = 0;
    
    while (1) {
        // visited[i][j]: 0 = unvisited, 1 = visited this round
        int visited[50][50] = {0};
        
        // For each region we will store:
        // regions[r] = list of cells in the region
        // threats[r] = set of threatened uninfected cells
        // walls[r]   = number of walls needed
        
        // Since n,m <= 50, we use simple arrays
        int regionCells[2500][2];   // temporary
        int threatCells[2500][2];
        int maxThreat = 0;
        int maxWalls = 0;
        int maxRegionStart = -1;    // starting index of the most threatening region
        int maxRegionSize = 0;
        
        int totalRegions = 0;
        
        // We will collect all candidate regions
        // To keep code simple we process one region at a time and keep only the best
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isInfected[i][j] == 1 && !visited[i][j]) {
                    // BFS / DFS to explore this region
                    int queue[2500][2];
                    int front = 0, rear = 0;
                    queue[rear][0] = i; queue[rear][1] = j; rear++;
                    visited[i][j] = 1;
                    
                    int cells[2500][2];
                    int cellCnt = 0;
                    cells[cellCnt][0] = i; cells[cellCnt][1] = j; cellCnt++;
                    
                    int threatSet[50][50] = {0};  // to unique threatened cells
                    int threatCnt = 0;
                    int wallCnt = 0;
                    
                    while (front < rear) {
                        int x = queue[front][0];
                        int y = queue[front][1];
                        front++;
                        
                        for (int d = 0; d < 4; d++) {
                            int nx = x + dirs[d][0];
                            int ny = y + dirs[d][1];
                            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                            
                            if (isInfected[nx][ny] == 1) {
                                if (!visited[nx][ny]) {
                                    visited[nx][ny] = 1;
                                    queue[rear][0] = nx; queue[rear][1] = ny; rear++;
                                    cells[cellCnt][0] = nx; cells[cellCnt][1] = ny; cellCnt++;
                                }
                            } else if (isInfected[nx][ny] == 0) {
                                wallCnt++;
                                if (!threatSet[nx][ny]) {
                                    threatSet[nx][ny] = 1;
                                    threatCnt++;
                                }
                            }
                        }
                    }
                    
                    // Now we have a region
                    if (threatCnt > maxThreat) {
                        maxThreat = threatCnt;
                        maxWalls = wallCnt;
                        maxRegionStart = 0; // we will re-store the cells
                        maxRegionSize = cellCnt;
                        // copy cells
                        for (int k = 0; k < cellCnt; k++) {
                            regionCells[k][0] = cells[k][0];
                            regionCells[k][1] = cells[k][1];
                        }
                    }
                }
            }
        }
        
        if (maxThreat == 0) break;   // no more threats
        
        // Contain the most threatening region
        ans += maxWalls;
        for (int k = 0; k < maxRegionSize; k++) {
            int x = regionCells[k][0];
            int y = regionCells[k][1];
            isInfected[x][y] = -1;   // mark as contained (will never spread)
        }
        
        // Spread the virus from all remaining infected cells
        int toInfect[2500][2];
        int infectCnt = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isInfected[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dirs[d][0];
                        int ny = j + dirs[d][1];
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n && isInfected[nx][ny] == 0) {
                            // mark for infection (avoid duplicates with a set or just set later)
                            // simple way: set a temporary flag
                            isInfected[nx][ny] = 2;  // temporary
                        }
                    }
                }
            }
        }
        
        // Convert temporary 2 to 1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isInfected[i][j] == 2) {
                    isInfected[i][j] = 1;
                }
            }
        }
    }
    
    return ans;
}