class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> domainCount = new HashMap<>();
        
        for (String cpdomain : cpdomains) {
            // Split the string into count and domain
            String[] parts = cpdomain.split(" ");
            int count = Integer.parseInt(parts[0]);
            String domain = parts[1];
            
            // Split the domain into subdomains
            String[] subdomains = domain.split("\\.");
            
            // Build subdomains from right to left
            String current = "";
            for (int i = subdomains.length - 1; i >= 0; i--) {
                if (i == subdomains.length - 1) {
                    current = subdomains[i];
                } else {
                    current = subdomains[i] + "." + current;
                }
                // Add the count to this subdomain
                domainCount.put(current, domainCount.getOrDefault(current, 0) + count);
            }
        }
        
        // Convert map to result list
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : domainCount.entrySet()) {
            result.add(entry.getValue() + " " + entry.getKey());
        }
        return result;
    }
}