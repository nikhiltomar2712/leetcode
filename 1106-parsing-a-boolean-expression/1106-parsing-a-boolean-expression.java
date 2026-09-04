class Solution {
    public boolean parseBoolExpr(String expression) {
        return eval(expression, new int[]{0});
    }
    
    private boolean eval(String expr, int[] idx) {
        char c = expr.charAt(idx[0]++);
        
        // Base cases
        if (c == 't') return true;
        if (c == 'f') return false;
        
        // Operator: !, &, or |
        char op = c;
        idx[0]++;                 // skip '('
        
        List<Boolean> values = new ArrayList<>();
        
        while (expr.charAt(idx[0]) != ')') {
            if (expr.charAt(idx[0]) == ',') {
                idx[0]++;         // skip comma
                continue;
            }
            values.add(eval(expr, idx));
        }
        idx[0]++;                 // skip ')'
        
        // Apply the operator
        if (op == '!') {
            return !values.get(0);
        } else if (op == '&') {
            for (boolean v : values) {
                if (!v) return false;
            }
            return true;
        } else { // op == '|'
            for (boolean v : values) {
                if (v) return true;
            }
            return false;
        }
    }
}