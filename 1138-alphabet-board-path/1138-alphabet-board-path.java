class Solution {
    public String alphabetBoardPath(String target) {
        StringBuilder result = new StringBuilder();
        int currRow = 0, currCol = 0;
        
        for (char ch : target.toCharArray()) {
            // Calculate target position on the board
            int targetRow = (ch - 'a') / 5;
            int targetCol = (ch - 'a') % 5;
            
            // Special handling for 'z' (last row, first column)
            // We need to move left before moving down when going from 'z'
            // or move up before moving right when going to 'z'
            if (ch == 'z') {
                // When going to 'z', move left first if needed
                while (currCol > targetCol) {
                    result.append('L');
                    currCol--;
                }
                while (currRow < targetRow) {
                    result.append('D');
                    currRow++;
                }
                while (currRow > targetRow) {
                    result.append('U');
                    currRow--;
                }
                // Now move left if still needed (shouldn't happen here)
                while (currCol > targetCol) {
                    result.append('L');
                    currCol--;
                }
            } else {
                // For all other letters, move up/down first, then left/right
                // This handles the case of coming from 'z'
                while (currRow > targetRow) {
                    result.append('U');
                    currRow--;
                }
                while (currRow < targetRow) {
                    result.append('D');
                    currRow++;
                }
                while (currCol > targetCol) {
                    result.append('L');
                    currCol--;
                }
                while (currCol < targetCol) {
                    result.append('R');
                    currCol++;
                }
            }
            
            // Add the current character
            result.append('!');
        }
        
        return result.toString();
    }
}