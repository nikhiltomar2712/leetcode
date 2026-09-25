import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Deque<List<String>> stack = new ArrayDeque<>();
        Set<String> union = new HashSet<>();
        Set<String> product = new HashSet<>();
        product.add("");
        
        for (char ch : expression.toCharArray()) {
            if (ch == '{') {
                stack.push(new ArrayList<>(union));
                stack.push(new ArrayList<>(product));
                union = new HashSet<>();
                product = new HashSet<>();
                product.add("");
            } else if (ch == '}') {
                // Add current product to union
                union.addAll(product);
                
                List<String> prevProduct = stack.pop();
                List<String> prevUnion = stack.pop();
                
                // Cartesian product of prevProduct and union
                Set<String> newProduct = new HashSet<>();
                for (String p : prevProduct) {
                    for (String u : union) {
                        newProduct.add(p + u);
                    }
                }
                
                product = newProduct;
                union = new HashSet<>(prevUnion);
            } else if (ch == ',') {
                union.addAll(product);
                product = new HashSet<>();
                product.add("");
            } else {
                Set<String> newProduct = new HashSet<>();
                for (String p : product) {
                    newProduct.add(p + ch);
                }
                product = newProduct;
            }
        }
        
        union.addAll(product);
        List<String> result = new ArrayList<>(union);
        Collections.sort(result);
        return result;
    }
}