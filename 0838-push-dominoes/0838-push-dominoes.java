class Solution {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        int[] forces = new int[n]; // net force: positive = right, negative = left
        
        // Scan left to right: propagate 'R' force
        int force = 0;
        for (int i = 0; i < n; i++) {
            char c = dominoes.charAt(i);
            if (c == 'R') {
                force = n; // reset to a large number (max distance)
            } else if (c == 'L') {
                force = 0; // 'L' stops rightward force
            } else if (force > 0) {
                force--; // force diminishes with distance
            }
            forces[i] += force;
        }
        
        // Scan right to left: propagate 'L' force (as negative)
        force = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = dominoes.charAt(i);
            if (c == 'L') {
                force = n; // reset to a large number
            } else if (c == 'R') {
                force = 0; // 'R' stops leftward force
            } else if (force > 0) {
                force--; // force diminishes with distance
            }
            forces[i] -= force;
        }
        
        // Build result based on net force
        StringBuilder result = new StringBuilder();
        for (int f : forces) {
            if (f > 0) result.append('R');
            else if (f < 0) result.append('L');
            else result.append('.');
        }
        return result.toString();
    }
}