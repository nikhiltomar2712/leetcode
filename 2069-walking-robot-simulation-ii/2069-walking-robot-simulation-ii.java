class Robot {
    private int width, height, perimeter;
    private int x, y;
    private int dir; // 0: East, 1: North, 2: West, 3: South

    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};
    private String[] dirNames = {"East", "North", "West", "South"};

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.perimeter = 2 * (width + height) - 4;
        this.x = 0;
        this.y = 0;
        this.dir = 0; // Start facing East
    }

    public void step(int num) {
        num %= perimeter;

        // If num is 0 after modulo, the robot has completed a full cycle.
        // We still need to update the direction if it's at the origin.
        if (num == 0) {
            if (x == 0 && y == 0) {
                dir = 3; // Facing South after a full cycle
            }
            return;
        }

        while (num > 0) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // Check if the next step is out of bounds
            if (nx < 0 || nx >= width || ny < 0 || ny >= height) {
                // Turn 90 degrees counterclockwise
                dir = (dir + 1) % 4;
                // After turning, we do not consume a step; we retry in the new direction
            } else {
                // Move forward
                x = nx;
                y = ny;
                num--;
            }
        }
    }

    public int[] getPos() {
        return new int[]{x, y};
    }

    public String getDir() {
        return dirNames[dir];
    }
}