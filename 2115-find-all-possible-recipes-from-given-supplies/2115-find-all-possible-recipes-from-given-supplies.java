class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> available = new HashSet<>(Arrays.asList(supplies));
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();
        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            indegree.put(recipe, ingredients.get(i).size());
            for (String ing : ingredients.get(i)) {
                graph.computeIfAbsent(ing, k -> new ArrayList<>()).add(recipe);
            }
        }
        Queue<String> queue = new LinkedList<>();
        for (String s : supplies) {
            queue.offer(s);
        }
        List<String> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (indegree.containsKey(cur) && indegree.get(cur) == 0) {
                res.add(cur);
            }
            for (String next : graph.getOrDefault(cur, new ArrayList<>())) {
                indegree.merge(next, -1, Integer::sum);
                if (indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        return res;
    }
}