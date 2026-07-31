class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        
        for (int asteroid : asteroids) {
            boolean destroyed = false;
            
            // Process collisions when current asteroid is moving left (negative)
            // and the top of stack is moving right (positive)
            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                int rightMoving = stack.peek();
                
                // Compare sizes: |asteroid| (left-moving) vs rightMoving (right-moving)
                if (Math.abs(asteroid) > rightMoving) {
                    // Current asteroid destroys the right-moving one
                    stack.pop();
                    // Continue checking against the next asteroid in stack
                } else if (Math.abs(asteroid) == rightMoving) {
                    // Both destroy each other
                    stack.pop();
                    destroyed = true;
                    break;
                } else {
                    // Right-moving asteroid is larger, current asteroid is destroyed
                    destroyed = true;
                    break;
                }
            }
            
            // If not destroyed, push current asteroid onto stack
            if (!destroyed) {
                stack.push(asteroid);
            }
        }
        
        // Convert stack to array (preserving order from bottom to top)
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }
}