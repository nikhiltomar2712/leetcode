class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        
        // Build graph: person -> list of people who are richer than them
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : richer) {
            graph.get(edge[1]).add(edge[0]); // edge[1] is poorer, edge[0] is richer
        }
        
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        
        for (int i = 0; i < n; i++) {
            dfs(i, graph, quiet, answer);
        }
        
        return answer;
    }
    
    private int dfs(int person, List<List<Integer>> graph, int[] quiet, int[] answer) {
        if (answer[person] != -1) {
            return answer[person];
        }
        
        // Start with the person themselves
        int quietestPerson = person;
        
        // Check all people who are richer than current person
        for (int richerPerson : graph.get(person)) {
            int candidate = dfs(richerPerson, graph, quiet, answer);
            if (quiet[candidate] < quiet[quietestPerson]) {
                quietestPerson = candidate;
            }
        }
        
        answer[person] = quietestPerson;
        return quietestPerson;
    }
}