class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // Map each email to its parent email (for union-find)
        Map<String, String> parent = new HashMap<>();
        // Map each email to the account name it belongs to
        Map<String, String> emailToName = new HashMap<>();
        // Map each email to its root parent after union
        Map<String, Set<String>> rootToEmails = new HashMap<>();
        
        // Step 1: Initialize DSU and map emails to names
        for (List<String> account : accounts) {
            String name = account.get(0);
            String firstEmail = account.get(1);
            
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                parent.putIfAbsent(email, email); // Each email is its own parent initially
                emailToName.put(email, name);
                
                // Union the first email with every other email in this account
                if (i == 1) continue;
                union(parent, firstEmail, email);
            }
        }
        
        // Step 2: Group emails by their root parent
        for (String email : parent.keySet()) {
            String root = find(parent, email);
            rootToEmails.computeIfAbsent(root, k -> new TreeSet<>()).add(email);
        }
        
        // Step 3: Build the result list
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : rootToEmails.entrySet()) {
            String rootEmail = entry.getKey();
            Set<String> emails = entry.getValue();
            String name = emailToName.get(rootEmail);
            
            List<String> mergedAccount = new ArrayList<>();
            mergedAccount.add(name);
            mergedAccount.addAll(emails); // TreeSet ensures sorted order
            result.add(mergedAccount);
        }
        
        return result;
    }
    
    // Union-Find (DSU) helper methods
    private String find(Map<String, String> parent, String email) {
        if (!parent.get(email).equals(email)) {
            parent.put(email, find(parent, parent.get(email)));
        }
        return parent.get(email);
    }
    
    private void union(Map<String, String> parent, String email1, String email2) {
        String root1 = find(parent, email1);
        String root2 = find(parent, email2);
        if (!root1.equals(root2)) {
            parent.put(root1, root2);
        }
    }
}