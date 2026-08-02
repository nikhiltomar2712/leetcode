class Solution {
    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        // Create variable-value mapping
        Map<String, Integer> varMap = new HashMap<>();
        for (int i = 0; i < evalvars.length; i++) {
            varMap.put(evalvars[i], evalints[i]);
        }
        
        // Parse and evaluate the expression
        Polynomial result = evaluate(expression, varMap);
        
        // Convert to required output format
        return result.toList();
    }
    
    class Term implements Comparable<Term> {
        int coefficient;
        List<String> variables;
        
        Term(int coefficient, List<String> variables) {
            this.coefficient = coefficient;
            this.variables = variables;
        }
        
        @Override
        public int compareTo(Term other) {
            // Sort by degree (descending), then lexicographically
            if (this.variables.size() != other.variables.size()) {
                return other.variables.size() - this.variables.size();
            }
            for (int i = 0; i < this.variables.size(); i++) {
                int cmp = this.variables.get(i).compareTo(other.variables.get(i));
                if (cmp != 0) return cmp;
            }
            return 0;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Term)) return false;
            Term other = (Term) obj;
            return this.variables.equals(other.variables);
        }
        
        @Override
        public int hashCode() {
            return variables.hashCode();
        }
    }
    
    class Polynomial {
        Map<Term, Integer> terms; // Term -> coefficient
        
        Polynomial() {
            this.terms = new HashMap<>();
        }
        
        Polynomial(int constant) {
            this.terms = new HashMap<>();
            if (constant != 0) {
                Term term = new Term(constant, new ArrayList<>());
                this.terms.put(term, constant);
            }
        }
        
        Polynomial(Term term, int coefficient) {
            this.terms = new HashMap<>();
            if (coefficient != 0) {
                this.terms.put(term, coefficient);
            }
        }
        
        Polynomial add(Polynomial other) {
            Polynomial result = new Polynomial();
            result.terms.putAll(this.terms);
            
            for (Map.Entry<Term, Integer> entry : other.terms.entrySet()) {
                Term term = entry.getKey();
                int coeff = entry.getValue();
                int newCoeff = result.terms.getOrDefault(term, 0) + coeff;
                if (newCoeff == 0) {
                    result.terms.remove(term);
                } else {
                    result.terms.put(term, newCoeff);
                }
            }
            return result;
        }
        
        Polynomial subtract(Polynomial other) {
            Polynomial result = new Polynomial();
            result.terms.putAll(this.terms);
            
            for (Map.Entry<Term, Integer> entry : other.terms.entrySet()) {
                Term term = entry.getKey();
                int coeff = entry.getValue();
                int newCoeff = result.terms.getOrDefault(term, 0) - coeff;
                if (newCoeff == 0) {
                    result.terms.remove(term);
                } else {
                    result.terms.put(term, newCoeff);
                }
            }
            return result;
        }
        
        Polynomial multiply(Polynomial other) {
            Polynomial result = new Polynomial();
            
            for (Map.Entry<Term, Integer> entry1 : this.terms.entrySet()) {
                for (Map.Entry<Term, Integer> entry2 : other.terms.entrySet()) {
                    Term term1 = entry1.getKey();
                    Term term2 = entry2.getKey();
                    int coeff1 = entry1.getValue();
                    int coeff2 = entry2.getValue();
                    
                    // Merge variables
                    List<String> newVars = new ArrayList<>();
                    newVars.addAll(term1.variables);
                    newVars.addAll(term2.variables);
                    Collections.sort(newVars);
                    
                    Term newTerm = new Term(1, newVars);
                    int newCoeff = result.terms.getOrDefault(newTerm, 0) + coeff1 * coeff2;
                    if (newCoeff == 0) {
                        result.terms.remove(newTerm);
                    } else {
                        result.terms.put(newTerm, newCoeff);
                    }
                }
            }
            return result;
        }
        
        List<String> toList() {
            List<String> result = new ArrayList<>();
            List<Term> sortedTerms = new ArrayList<>(terms.keySet());
            Collections.sort(sortedTerms);
            
            for (Term term : sortedTerms) {
                int coeff = terms.get(term);
                StringBuilder sb = new StringBuilder();
                sb.append(coeff);
                for (String var : term.variables) {
                    sb.append("*").append(var);
                }
                result.add(sb.toString());
            }
            
            return result;
        }
    }
    
    private Polynomial evaluate(String expression, Map<String, Integer> varMap) {
        int i = 0;
        return parseExpression(expression, i, varMap);
    }
    
    private Polynomial parseExpression(String s, int index, Map<String, Integer> varMap) {
        // Simple recursive descent parser
        return parseAddSubtract(s, new int[]{0}, varMap);
    }
    
    private Polynomial parseAddSubtract(String s, int[] index, Map<String, Integer> varMap) {
        Polynomial left = parseMultiplyDivide(s, index, varMap);
        
        while (index[0] < s.length()) {
            char op = s.charAt(index[0]);
            if (op == '+') {
                index[0]++;
                skipSpaces(s, index);
                Polynomial right = parseMultiplyDivide(s, index, varMap);
                left = left.add(right);
            } else if (op == '-') {
                index[0]++;
                skipSpaces(s, index);
                Polynomial right = parseMultiplyDivide(s, index, varMap);
                left = left.subtract(right);
            } else if (op == ')') {
                break;
            } else {
                break;
            }
        }
        
        return left;
    }
    
    private Polynomial parseMultiplyDivide(String s, int[] index, Map<String, Integer> varMap) {
        Polynomial left = parsePrimary(s, index, varMap);
        
        while (index[0] < s.length()) {
            char op = s.charAt(index[0]);
            if (op == '*') {
                index[0]++;
                skipSpaces(s, index);
                Polynomial right = parsePrimary(s, index, varMap);
                left = left.multiply(right);
            } else {
                break;
            }
        }
        
        return left;
    }
    
    private Polynomial parsePrimary(String s, int[] index, Map<String, Integer> varMap) {
        skipSpaces(s, index);
        
        if (index[0] >= s.length()) {
            return new Polynomial(0);
        }
        
        char c = s.charAt(index[0]);
        
        if (c == '(') {
            index[0]++; // skip '('
            Polynomial result = parseAddSubtract(s, index, varMap);
            index[0]++; // skip ')'
            skipSpaces(s, index);
            return result;
        } else if (Character.isDigit(c)) {
            // Parse number
            int num = 0;
            while (index[0] < s.length() && Character.isDigit(s.charAt(index[0]))) {
                num = num * 10 + (s.charAt(index[0]) - '0');
                index[0]++;
            }
            skipSpaces(s, index);
            return new Polynomial(num);
        } else if (Character.isLetter(c)) {
            // Parse variable
            StringBuilder varName = new StringBuilder();
            while (index[0] < s.length() && Character.isLetter(s.charAt(index[0]))) {
                varName.append(s.charAt(index[0]));
                index[0]++;
            }
            String var = varName.toString();
            skipSpaces(s, index);
            
            if (varMap.containsKey(var)) {
                return new Polynomial(varMap.get(var));
            } else {
                List<String> vars = new ArrayList<>();
                vars.add(var);
                Term term = new Term(1, vars);
                return new Polynomial(term, 1);
            }
        }
        
        return new Polynomial(0);
    }
    
    private void skipSpaces(String s, int[] index) {
        while (index[0] < s.length() && s.charAt(index[0]) == ' ') {
            index[0]++;
        }
    }
}