class Solution {
    public List<String> braceExpansionII(String expression) {
        // Evaluate the whole expression
        Set<String> result = evaluate(expression, 0, expression.length());
        
        // Convert to sorted list
        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }
    
    // Evaluate expression[start ... end)
    private Set<String> evaluate(String expr, int start, int end) {
        // We process the expression as a sequence of "terms" separated by ','
        // Each term is a product of factors (letters or nested {...})
        
        Set<String> union = new HashSet<>();
        Set<String> product = new HashSet<>();
        product.add("");               // identity for product
        
        int i = start;
        while (i < end) {
            char c = expr.charAt(i);
            
            if (c == '{') {
                // Find matching closing brace
                int j = i + 1, balance = 1;
                while (balance > 0) {
                    if (expr.charAt(j) == '{') balance++;
                    else if (expr.charAt(j) == '}') balance--;
                    j++;
                }
                // Recursively evaluate the inside of the braces
                Set<String> nested = evaluate(expr, i + 1, j - 1);
                
                // Cartesian product with current product
                product = multiply(product, nested);
                i = j;
            } 
            else if (c == ',') {
                // End of current product term → add to union
                union.addAll(product);
                product = new HashSet<>();
                product.add("");
                i++;
            } 
            else if (c == '}') {
                // Should not reach here because we skip balanced braces
                i++;
            } 
            else {
                // Single letter → treat as a singleton set
                Set<String> letter = new HashSet<>();
                letter.add(String.valueOf(c));
                product = multiply(product, letter);
                i++;
            }
        }
        
        // Add the last product term
        union.addAll(product);
        return union;
    }
    
    // Cartesian product of two sets of strings
    private Set<String> multiply(Set<String> a, Set<String> b) {
        Set<String> res = new HashSet<>();
        for (String x : a) {
            for (String y : b) {
                res.add(x + y);
            }
        }
        return res;
    }
}