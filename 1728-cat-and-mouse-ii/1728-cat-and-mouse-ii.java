class Solution {
    private static final int MOUSE_TURN = 0, CAT_TURN = 1;
    private static final int DRAW = 0, MOUSE_WIN = 1, CAT_WIN = 2;
    private int[][] grid;
    private int rows, cols;
    private int[][][] degree;
    private int[][][] result;

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        rows = grid.length;
        cols = grid[0].length();
        this.grid = new int[rows][cols];
        int mouseStart = -1, catStart = -1, foodPos = -1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = grid[i].charAt(j);
                if (c == 'M') {
                    mouseStart = i * cols + j;
                } else if (c == 'C') {
                    catStart = i * cols + j;
                } else if (c == 'F') {
                    foodPos = i * cols + j;
                }
                this.grid[i][j] = (c == '#') ? 1 : 0;
            }
        }

        degree = new int[rows * cols][rows * cols][2];
        result = new int[rows * cols][rows * cols][2];

        // Compute degrees
        for (int mi = 0; mi < rows * cols; mi++) {
            int mr = mi / cols, mc = mi % cols;
            if (this.grid[mr][mc] == 1) continue;
            for (int ci = 0; ci < rows * cols; ci++) {
                int cr = ci / cols, cc = ci % cols;
                if (this.grid[cr][cc] == 1) continue;
                degree[mi][ci][MOUSE_TURN] = countMoves(mr, mc, mouseJump);
                degree[mi][ci][CAT_TURN] = countMoves(cr, cc, catJump);
            }
        }

        // Initialize BFS queue
        Deque<int[]> queue = new ArrayDeque<>();

        // Mouse wins: mouse reaches food, or cat reaches food, or mouse == cat (cat catches mouse)
        for (int ci = 0; ci < rows * cols; ci++) {
            if (this.grid[ci / cols][ci % cols] == 1) continue;

            // Mouse at food
            result[foodPos][ci][MOUSE_TURN] = MOUSE_WIN;
            result[foodPos][ci][CAT_TURN] = MOUSE_WIN;
            queue.offer(new int[]{foodPos, ci, MOUSE_TURN});
            queue.offer(new int[]{foodPos, ci, CAT_TURN});

            // Cat at food
            result[ci][foodPos][MOUSE_TURN] = CAT_WIN;
            result[ci][foodPos][CAT_TURN] = CAT_WIN;
            queue.offer(new int[]{ci, foodPos, MOUSE_TURN});
            queue.offer(new int[]{ci, foodPos, CAT_TURN});

            // Mouse == Cat
            result[ci][ci][MOUSE_TURN] = CAT_WIN;
            result[ci][ci][CAT_TURN] = CAT_WIN;
            queue.offer(new int[]{ci, ci, MOUSE_TURN});
            queue.offer(new int[]{ci, ci, CAT_TURN});
        }

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int mouse = state[0], cat = state[1], turn = state[2];
            int status = result[mouse][cat][turn];

            int prevTurn = 1 - turn;

            if (prevTurn == MOUSE_TURN) {
                // Previous position was mouse's turn; mouse moved from some prevMouse
                List<Integer> prevMouses = getNeighbors(mouse, mouseJump);
                for (int prevMouse : prevMouses) {
                    if (result[prevMouse][cat][MOUSE_TURN] != DRAW) continue;

                    if (status == MOUSE_WIN) {
                        result[prevMouse][cat][MOUSE_TURN] = MOUSE_WIN;
                        queue.offer(new int[]{prevMouse, cat, MOUSE_TURN});
                    } else {
                        if (--degree[prevMouse][cat][MOUSE_TURN] == 0) {
                            result[prevMouse][cat][MOUSE_TURN] = CAT_WIN;
                            queue.offer(new int[]{prevMouse, cat, MOUSE_TURN});
                        }
                    }
                }
            } else {
                // Previous position was cat's turn; cat moved from some prevCat
                List<Integer> prevCats = getNeighbors(cat, catJump);
                for (int prevCat : prevCats) {
                    if (result[mouse][prevCat][CAT_TURN] != DRAW) continue;

                    if (status == CAT_WIN) {
                        result[mouse][prevCat][CAT_TURN] = CAT_WIN;
                        queue.offer(new int[]{mouse, prevCat, CAT_TURN});
                    } else {
                        if (--degree[mouse][prevCat][CAT_TURN] == 0) {
                            result[mouse][prevCat][CAT_TURN] = MOUSE_WIN;
                            queue.offer(new int[]{mouse, prevCat, CAT_TURN});
                        }
                    }
                }
            }
        }

        return result[mouseStart][catStart][MOUSE_TURN] == MOUSE_WIN;
    }

    private int countMoves(int r, int c, int jump) {
        int count = 1; // stay
        for (int[] d : DIRS) {
            for (int step = 1; step <= jump; step++) {
                int nr = r + d[0] * step;
                int nc = c + d[1] * step;
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || grid[nr][nc] == 1) break;
                count++;
            }
        }
        return count;
    }

    private List<Integer> getNeighbors(int pos, int jump) {
        int r = pos / cols, c = pos % cols;
        List<Integer> neighbors = new ArrayList<>();
        neighbors.add(pos); // stay
        for (int[] d : DIRS) {
            for (int step = 1; step <= jump; step++) {
                int nr = r + d[0] * step;
                int nc = c + d[1] * step;
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || grid[nr][nc] == 1) break;
                neighbors.add(nr * cols + nc);
            }
        }
        return neighbors;
    }
}