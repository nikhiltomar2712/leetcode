import java.util.*;

class Solution {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int x = location.get(0), y = location.get(1);
        List<Double> angles = new ArrayList<>();
        int sameLocation = 0;

        for (List<Integer> p : points) {
            int dx = p.get(0) - x;
            int dy = p.get(1) - y;
            if (dx == 0 && dy == 0) {
                sameLocation++;
                continue;
            }
            angles.add(Math.atan2(dy, dx));
        }

        Collections.sort(angles);

        int n = angles.size();
        // Duplicate with +2π to handle circular wrap-around
        for (int i = 0; i < n; i++) {
            angles.add(angles.get(i) + 2 * Math.PI);
        }

        double fov = Math.toRadians(angle);
        int maxVisible = 0;
        int left = 0;

        for (int right = 0; right < angles.size(); right++) {
            while (angles.get(right) - angles.get(left) > fov + 1e-9) {
                left++;
            }
            maxVisible = Math.max(maxVisible, right - left + 1);
        }

        return maxVisible + sameLocation;
    }
}