class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> contentToPaths = new HashMap<>();
        
        for (String path : paths) {
            String[] parts = path.split(" ");
            String directory = parts[0];
            
            for (int i = 1; i < parts.length; i++) {
                String fileInfo = parts[i];
                int openParen = fileInfo.indexOf('(');
                int closeParen = fileInfo.indexOf(')');
                
                String fileName = fileInfo.substring(0, openParen);
                String content = fileInfo.substring(openParen + 1, closeParen);
                
                String fullPath = directory + "/" + fileName;
                
                contentToPaths.computeIfAbsent(content, k -> new ArrayList<>()).add(fullPath);
            }
        }
        
        List<List<String>> result = new ArrayList<>();
        for (List<String> pathList : contentToPaths.values()) {
            if (pathList.size() > 1) {
                result.add(pathList);
            }
        }
        
        return result;
    }
}