class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        Map<List<Integer>, Integer> memo = new HashMap<>();
        return dfs(price, special, needs, memo);
    }

    private int dfs(List<Integer> price, List<List<Integer>> special, 
                    List<Integer> needs, Map<List<Integer>, Integer> memo) {
        
        if (memo.containsKey(needs)) {
            return memo.get(needs);
        }

        // Cost if we buy everything at regular price
        int minCost = 0;
        for (int i = 0; i < needs.size(); i++) {
            minCost += needs.get(i) * price.get(i);
        }

        // Try every special offer
        for (List<Integer> offer : special) {
            List<Integer> nextNeeds = new ArrayList<>();
            boolean valid = true;

            for (int i = 0; i < needs.size(); i++) {
                if (needs.get(i) < offer.get(i)) {
                    valid = false;
                    break;
                }
                nextNeeds.add(needs.get(i) - offer.get(i));
            }

            if (valid) {
                int offerPrice = offer.get(offer.size() - 1);
                minCost = Math.min(minCost, offerPrice + dfs(price, special, nextNeeds, memo));
            }
        }

        memo.put(new ArrayList<>(needs), minCost);
        return minCost;
    }
}