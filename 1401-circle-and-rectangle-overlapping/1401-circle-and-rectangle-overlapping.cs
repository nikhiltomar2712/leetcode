public class Solution {
    public bool CheckOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the closest point on the rectangle to the circle's center
        int closestX = Math.Clamp(xCenter, x1, x2);
        int closestY = Math.Clamp(yCenter, y1, y2);

        // Compute squared distance from center to that closest point
        int dx = xCenter - closestX;
        int dy = yCenter - closestY;
        int distSq = dx * dx + dy * dy;

        // Overlap if distance <= radius
        return distSq <= radius * radius;
    }
}