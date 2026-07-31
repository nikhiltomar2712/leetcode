class Solution {
    public int evaluate(String expression) {
        // Map to store variables in the current scope
        Map<String, Integer> scope = new HashMap<>();
        return evaluate(expression, scope);
    }
    
    private int evaluate(String expr, Map<String, Integer> scope) {
        // Case 1: Integer literal (positive or negative)
        if (expr.charAt(0) == '-' || Character.isDigit(expr.charAt(0))) {
            return Integer.parseInt(expr);
        }
        
        // Case 2: Variable reference
        if (expr.charAt(0) != '(') {
            return scope.get(expr);
        }
        
        // Case 3: Let, add, or mult expression
        // Remove outer parentheses and split into tokens
        String content = expr.substring(1, expr.length() - 1);
        String[] tokens = splitTokens(content);
        
        String command = tokens[0];
        
        if (command.equals("let")) {
            // Create new scope for let expression
            Map<String, Integer> newScope = new HashMap<>(scope);
            
            // Process pairs of (variable, expression)
            int i = 1;
            while (i < tokens.length - 1) {
                String var = tokens[i++];
                String valueExpr = tokens[i++];
                int value = evaluate(valueExpr, newScope);
                newScope.put(var, value);
            }
            
            // Evaluate the final expression
            return evaluate(tokens[i], newScope);
            
        } else if (command.equals("add")) {
            int left = evaluate(tokens[1], scope);
            int right = evaluate(tokens[2], scope);
            return left + right;
            
        } else if (command.equals("mult")) {
            int left = evaluate(tokens[1], scope);
            int right = evaluate(tokens[2], scope);
            return left * right;
        }
        
        return 0; // Should never reach here
    }
    
    // Helper to split expression into tokens, handling nested parentheses
    private String[] splitTokens(String expr) {
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int balance = 0;
        
        for (char c : expr.toCharArray()) {
            if (c == '(') {
                balance++;
                current.append(c);
            } else if (c == ')') {
                balance--;
                current.append(c);
            } else if (c == ' ' && balance == 0) {
                // Space at top level - token boundary
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
            } else {
                current.append(c);
            }
        }
        
        // Add the last token
        if (current.length() > 0) {
            tokens.add(current.toString());
        }
        
        return tokens.toArray(new String[0]);
    }
}