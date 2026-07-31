class Solution {
    public List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        boolean inBlockComment = false;
        
        for (String line : source) {
            int i = 0;
            int n = line.length();
            
            while (i < n) {
                // If we are inside a block comment
                if (inBlockComment) {
                    // Look for the end of block comment "*/"
                    if (i + 1 < n && line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
                        inBlockComment = false;
                        i += 2; // Skip the "*/"
                    } else {
                        i++; // Ignore character inside block comment
                    }
                } 
                // Not in a block comment
                else {
                    // Check for start of line comment "//"
                    if (i + 1 < n && line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
                        break; // Ignore rest of line
                    } 
                    // Check for start of block comment "/*"
                    else if (i + 1 < n && line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
                        inBlockComment = true;
                        i += 2; // Skip the "/*"
                    } 
                    // Regular character
                    else {
                        currentLine.append(line.charAt(i));
                        i++;
                    }
                }
            }
            
            // After processing line, if not in block comment and line has content
            if (!inBlockComment && currentLine.length() > 0) {
                result.add(currentLine.toString());
                currentLine.setLength(0); // Reset for next line
            }
        }
        
        return result;
    }
}