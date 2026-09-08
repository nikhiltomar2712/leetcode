class Solution {
    public List<String> removeSubfolders(String[] folder) {
        // Sort lexicographically so parent folders come before their subfolders
        Arrays.sort(folder);
        
        List<String> result = new ArrayList<>();
        String prev = folder[0];
        result.add(prev);
        
        for (int i = 1; i < folder.length; i++) {
            String current = folder[i];
            // Check if current is a subfolder of the last added folder
            // A subfolder must start with parent + "/"
            if (!current.startsWith(prev + "/")) {
                result.add(current);
                prev = current;
            }
        }
        return result;
    }
}