class Solution {
    public String countOfAtoms(String formula) {
        int n = formula.length();
        Stack<Map<String, Integer>> stack = new Stack<>();
        stack.push(new HashMap<>()); // Main map to store final counts
        
        int i = 0;
        
        while (i < n) {
            char c = formula.charAt(i);
            
            if (c == '(') {
                // Start a new nested scope
                stack.push(new HashMap<>());
                i++;
            } 
            else if (c == ')') {
                // End of current scope
                Map<String, Integer> currentMap = stack.pop();
                i++;
                
                // Get the multiplier if any
                int multiplier = 1;
                if (i < n && Character.isDigit(formula.charAt(i))) {
                    multiplier = 0;
                    while (i < n && Character.isDigit(formula.charAt(i))) {
                        multiplier = multiplier * 10 + (formula.charAt(i) - '0');
                        i++;
                    }
                }
                
                // Multiply all counts in currentMap by multiplier and add to parent
                Map<String, Integer> parentMap = stack.peek();
                for (Map.Entry<String, Integer> entry : currentMap.entrySet()) {
                    String element = entry.getKey();
                    int count = entry.getValue() * multiplier;
                    parentMap.put(element, parentMap.getOrDefault(element, 0) + count);
                }
            } 
            else if (Character.isUpperCase(c)) {
                // Parse the element name
                StringBuilder element = new StringBuilder();
                element.append(c);
                i++;
                
                // Parse lowercase letters (if any)
                while (i < n && Character.isLowerCase(formula.charAt(i))) {
                    element.append(formula.charAt(i));
                    i++;
                }
                
                // Parse the count (if any)
                int count = 1;
                if (i < n && Character.isDigit(formula.charAt(i))) {
                    count = 0;
                    while (i < n && Character.isDigit(formula.charAt(i))) {
                        count = count * 10 + (formula.charAt(i) - '0');
                        i++;
                    }
                }
                
                // Add to the current scope map
                Map<String, Integer> currentMap = stack.peek();
                currentMap.put(element.toString(), 
                               currentMap.getOrDefault(element.toString(), 0) + count);
            }
        }
        
        // Build the result string from the main map (sorted by element name)
        Map<String, Integer> finalCounts = stack.pop();
        StringBuilder result = new StringBuilder();
        
        for (Map.Entry<String, Integer> entry : new TreeMap<>(finalCounts).entrySet()) {
            result.append(entry.getKey());
            if (entry.getValue() > 1) {
                result.append(entry.getValue());
            }
        }
        
        return result.toString();
    }
}