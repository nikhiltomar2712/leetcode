class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        // Reduce the larger coordinate while both are still greater than the start
        while (tx > sx && ty > sy && tx != ty) {
            if (tx > ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }

        // Exact match
        if (tx == sx && ty == sy) {
            return true;
        }

        // One coordinate matches; the other must be reachable by adding the fixed one
        if (tx == sx) {
            return ty > sy && (ty - sy) % sx == 0;
        }
        if (ty == sy) {
            return tx > sx && (tx - sx) % sy == 0;
        }

        return false;
    }
}